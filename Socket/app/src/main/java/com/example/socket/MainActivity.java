package com.example.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Socket client;
    Button btnSendData;
    TextView textView;
    EditText editText;
    int fileSize;
    boolean isJson = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendData = findViewById(R.id.btn_send_data);
        textView = findViewById(R.id.tv_data);
        editText = findViewById(R.id.edit_text);

        new RecieveDataFromServer().execute();

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String msg = editText.getText().toString();
                            editText.getText().clear();
                            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
                            writer.write(msg);
                            writer.flush();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });
    }

    private class RecieveDataFromServer extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InetAddress address = InetAddress.getByName("192.168.1.9"); // 192.168.1.9     192.168.1.112
                client = new Socket();
                client.connect(new InetSocketAddress(address, 1100), 5000);

                String result = "";
                byte buffer[] = new byte[1024];

                InputStream is =  client.getInputStream();
                FileOutputStream fos = null;
                BufferedOutputStream bos = null;

                int bytesRead;
                long size = 0;
                long fileSize = 0;
                String fileName = "";
                boolean recvFileDone = false;

                while (client.isConnected() && !client.isClosed()) {
                    bytesRead = is.read(buffer, 0, buffer.length);
                    if (bytesRead <= 0 ) {
                        break;
                    }
                    size += bytesRead;

                    result = new String(buffer, 0, bytesRead);
                    publishProgress(result);

                    //paser buffer to get fileName and fileSize
                    if (fileSize <= 0) {
                        int position = result.indexOf('*');
                        int stringLength = result.length();
                        fileName = result.substring(0, position);

                        //Log.v("SIZEOFBYTE", "file name: " + fileName);
                        //Log.v("SIZEOFBYTE", "file size: " + result.substring(position + 1, stringLength));

                        String path = getBaseContext().getExternalFilesDir("myfiles").getPath(); //access SD Card
                        File file = new File(path + "/" + fileName);

                        fos =  new FileOutputStream(file);
                        bos = new BufferedOutputStream(fos);
                        try {
                            fileSize = Long.parseLong(result.substring(position + 1,stringLength));
                            Log.v("SIZEOFBYTE", "size bytes is: " + Long.toString(fileSize));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        // write in file
                        if (!recvFileDone) {
                            bos.write(buffer, 0, bytesRead);
                        }

                        if (size >= fileSize ) {
                            recvFileDone = true;
                            Log.v("SIZEOFBYTE", "size bytes: " + Long.toString(size));
                            bos.close();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            /*
            if (isJson) {
                isJson = false;
                try {
                    //{"name":"VangAnh","sex":"femal","age":18}
                    JSONObject jObj = new JSONObject(values[0]);
                    String name = jObj.getString("name");
                    String sex = jObj.getString("sex");
                    int age = jObj.getInt("age");

                    values[0] = "Json: \nname: " + name + "\nsex: " + sex + "\nage: " + Integer.toString(age);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            */
            /*try {
                String path = getBaseContext().getExternalFilesDir("myfiles").getPath(); //access SD Card
                File file = new File(path + "/text1.txt");
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
                writer.write(values[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/

            textView.append(values[0] + "\n");


        }
    }

}
