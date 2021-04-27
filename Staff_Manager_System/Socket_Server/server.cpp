#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <cstdio>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <sys/stat.h>
#include <fcntl.h>
#include<sys/types.h>
#include <iostream>
#include <Windows.h>

using namespace std;

#define PORT1 1100
#define PORT2 1010 

SOCKET s1, s2, client, androidDevice;
char buf[1024];
HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
CONSOLE_SCREEN_BUFFER_INFO cursorInfo;

void gotoxy(int x, int y)
{
    COORD coord;
    coord.X = x;
    coord.Y = y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void sendFile(SOCKET s, const char *fileName, int offset) {
    char bufferSend[1024];
    char header[256]; // header gom ten file + kich co file
    char fileSize[256];
    
    strcpy(header, fileName); // Gan ten file vao header
    strcat(header, "*");    
    
    FILE *fp = fopen(fileName,"rb");
    
    if(fp == NULL)
    {
        printf("Loi mo file.\n");  
        return;
    }   

    // Lay kich co cua file
    fseek(fp, 0, SEEK_END); // Dua con tro den cuoi file
    long long size = ftell(fp); // Lay kich co cua file
    fseek(fp, 0, SEEK_SET); // Dua con tro ve dau file
    sprintf(fileSize, "%lld", size);
    
    // header = fileName*fileSize
    strcat(header, fileSize);
    strcat(header, "\0");

    // Gui header
    send(s, header , strlen(header), 0);
    //send(s, fileSize , strlen(fileSize), 0);
    sleep(1);
    GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);
    int xCoord = cursorInfo.dwCursorPosition.X;
    int yCoord = cursorInfo.dwCursorPosition.Y;
    int bytesRead = 0;

    while(!feof(fp))
    {
        // Doc file
        int len;
        len = fread(bufferSend,sizeof(char), 1023,fp);
        bufferSend[len] = '\0';
        bytesRead += len;
        gotoxy(xCoord, yCoord);
        cout << "Send " << fileName << ": " << (bytesRead * 100 / size) << "%" << endl;

        //cout << bufferSend;
        // Gui du lieu toi client
        send(s, bufferSend, len + offset, 0);
    }
    
    // Ket thuc gui file
    fclose(fp);
}

void receiveFile(SOCKET soc, int offset) {
    char bufReceive[1024];
    char fileName[256];
    char charFileSize[256];
    
    strcpy(fileName, "fromserver_");
    strcpy(charFileSize, "0");
    
    recv(soc, bufReceive, 1024, 0); // Lay header=fileName*charFileSize
    //cout << bufReceive << "***" << strlen(bufReceive) << endl;;
    
    bool check = false;
    for (int i = 0; i < strlen(bufReceive); i++) {
        if (bufReceive[i] == '*') {
            check = true;
            continue;
        }
        if (!check) {
            int length = strlen(fileName);
            fileName[length] = bufReceive[i];
            fileName[length + 1] = '\0';
        }
        else {
            int length = strlen(charFileSize);
            charFileSize[length] = bufReceive[i];
            charFileSize[length + 1] = '\0';
        }
    }
    
    //cout << "file name: " << fileName << endl;
    //cout << "file size: " << charFileSize << endl;
    
    long long fileSize;
    fileSize = atoll(charFileSize); // Convert tu string sang long long
    
    FILE *fp = fopen(fileName, "wb"); 
    if(NULL == fp){
     printf("Tao file bi loi.\n");
    }
    
    GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);
    int xCoord = cursorInfo.dwCursorPosition.X;
    int yCoord = cursorInfo.dwCursorPosition.Y;
    
    int bytesReceived;
    // Nhan du lieu
    while(bytesReceived = recv(soc, bufReceive, 1024, 0))
    {     
        fwrite(bufReceive, 1,bytesReceived - offset,fp);
        //cout << bytesReceived << endl;
        gotoxy(xCoord, yCoord);
        cout << "Received " << fileName << ": " << (ftell(fp) * 100 / fileSize) << "%" << endl;
        if (fileSize == ftell(fp)) // Kiem tra xem da nhan du du lieu hay chua
            break;
    }
    
    // Ket thuc nhan file
    fclose(fp);
}

void receiveMessage(SOCKET s) {
    while(1) { 
        // Nhan du lieu tu client
        int ret = recv(s, buf, sizeof(buf), 0);

        if (ret <= 0)
        {
            ret = WSAGetLastError();
            printf("Ket noi bi ngat\n");
            closesocket(s);
            //listen(s, 5);
    
            // Chap nhan ket noi moi
            s = accept(s1, NULL, NULL);
            printf("Accepted Android Device: %d\n", s);
            continue;
        } 
       
        // Them ky tu ket thuc xau
        if (ret < sizeof(buf)) buf[ret] = 0;
        
        // In ra man hinh du lieu nhan duoc
        printf("Received message: %s\n", buf);
        
        if (buf[0] == '1' && buf[1] == '0' && buf[2] == '0' && buf[3] == '0') {
            const char* response = "readytoreceive";
            int checkStatus = send(s, response, strlen(response), 0); // Response to android device
        
            if (checkStatus == strlen(response)) {
                // Receive file from android device
                receiveFile(s, 0); 
            }
            
        } else if (buf[0] == '2' && buf[1] == '0' && buf[2] == '0' && buf[3] == '0') {
            const char* response = "readytosend";
            int checkStatus = send(s, response, strlen(response), 0); // Response to android device
        
            if (checkStatus == strlen(response)) {
                // Send file to android device
                sendFile(s, "fromserver_db", 0); 
            }
            
        }
        
        cout << "File Tranfer Completed..." << endl;
        // Gui message nhan tu client sang Android Device
        //send(androidDevice, buf, strlen(buf), 0);
        

        
    }
}

void *thread1_function(void *arg) {
    /* Chuyen sang trang thai cho ket noi
    int listen(SOCKET s, int backlog); backlog: chieu dai hang doi chap nhan ket noi
    */
    listen(s1, 5);
    
    // Chap nhan ket noi moi
    androidDevice = accept(s1, NULL, NULL);
    printf("Accepted Android Device: %d\n", androidDevice);
    
    // Gui file den android device
    //sendFile(androidDevice, "Mau_bao_cao.doc", 0);
    
    // Nhan thong diep tu Android Device
    receiveMessage(androidDevice);

    // Nhan file tu Android Device
    //receiveFile(androidDevice, 0);    
    
    closesocket(androidDevice);
    closesocket(s1);
    
    pthread_exit(NULL);
}

void *thread2_function(void *arg) {
    listen(s2, 5);
    
    // Chap nhan ket noi moi
    client = accept(s2, NULL, NULL);
    printf("Accepted client: %d\n", client);
    
    // Gui file den Cilent
    //sendFile(client, "i1.png", 1);
    
    // Nhan file tu client
    receiveFile(client, 1);
    
    // Nhan thong diep tu client
    receiveMessage(client);    
        
    closesocket(client);
    closesocket(s2);
    
    pthread_exit(NULL);
}

int main()
{       
    // Khoi tao thu vien Winsock
    WSADATA wsa;
    WSAStartup(MAKEWORD(2, 2), &wsa); //set up version 2.2

    // Tao socket cho ket noi
    s1 = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    s2 = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);

    // Khai bao dia chi server cho socket1
    SOCKADDR_IN addr1;
    addr1.sin_family = AF_INET;
    addr1.sin_addr.s_addr = htonl(INADDR_ANY);
    addr1.sin_port = htons(PORT1);
    
    // Khai bao dia chi server cho socket2
    SOCKADDR_IN addr2;
    addr2.sin_family = AF_INET;
    addr2.sin_addr.s_addr = htonl(INADDR_ANY);
    addr2.sin_port = htons(PORT2);

    // Gan dia chi voi socket
    bind(s1, (SOCKADDR*)&addr1, sizeof(addr1));
    bind(s2, (SOCKADDR*)&addr2, sizeof(addr2));

    printf("Wating for client...\n");
    
    //set up multithreads  
    int res1, res2;
    pthread_t thread1;
    pthread_t thread2;
    void *thread_result;
    
    //create threads
    res1 = pthread_create(&thread1, NULL, thread1_function, NULL);
    res2 = pthread_create(&thread2, NULL, thread2_function, NULL);
    
    if (res1 != 0 || res2 != 0) {
        perror("Thread creation failed");
        exit(EXIT_FAILURE);
    }
    
    res1 = pthread_join(thread1, &thread_result);
    res2 = pthread_join(thread2, &thread_result);
    
    if (res1 != 0 || res2 != 0) {
        perror("Thread join failed");
        exit(EXIT_FAILURE);
    }
    printf("Thread joined, it returned %s\n", (char *)thread_result);

    WSACleanup();
    
    system("pause");
    return 0;
}


