package com.example.quanlycanbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycanbo.models.CanBo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Socket client;
    InetAddress address;
    ItemAdapter adapter;
    ListView listView;
    List<CanBo> canBoList;
    AutoCompleteTextView ac_search;
    AutoCompleteItemAdapter ac_adapter;
    SQLiteDatabase db;
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new Socket();
        try {
            address = InetAddress.getByName("192.168.1.9"); // 192.168.1.9     192.168.1.112      192.168.43.42
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectToServer();

        openDB();
        createTable();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý Cán bộ");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_bar_color));

        canBoList = new ArrayList<CanBo>();
        listView = findViewById(R.id.list_view);
        ac_search = findViewById(R.id.actv_search);
        customDialog = new CustomDialog(MainActivity.this, getApplicationContext(), db);
        displayListView();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PersonalInfor.class);
                intent.putExtra("data", canBoList.get(position));
                startActivity(intent);
            }
        });

        ac_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PersonalInfor.class);
                TextView tv = view.findViewById(R.id.tv_id);
                intent.putExtra("data", canBoList.get(Integer.valueOf(tv.getText().toString()) - 1));
                startActivity(intent);
            }
        });

    }

    public void displayListView() {
        canBoList.clear();

        String sql = "select * from ThongTinCaNhan";
        Cursor cs = db.rawQuery(sql, null);

        cs.moveToFirst();
        if ( cs.getCount() > 0 ) {

            do {
                String ma_can_bo = cs.getString(0);
                String ho_ten = cs.getString(1);
                String ngay_sinh = cs.getString(cs.getColumnIndex("ngay_sinh"));
                String gioi_tinh = cs.getString(cs.getColumnIndex("gioi_tinh"));
                String chuc_danh = cs.getString(cs.getColumnIndex("chuc_danh"));
                String chuc_vu = cs.getString(cs.getColumnIndex("chuc_vu"));
                double hs_luong = cs.getDouble(cs.getColumnIndex("hs_luong"));
                double phu_cap_chuc_vu = cs.getDouble(cs.getColumnIndex("phu_cap_chuc_vu"));
                double phu_cap_giang_day = cs.getDouble(cs.getColumnIndex("phu_cap_giang_day"));
                String ma_so_thue = cs.getString(cs.getColumnIndex("ma_so_thue"));
                String so_tai_khoan = cs.getString(cs.getColumnIndex("so_tai_khoan"));
                String dia_chi = cs.getString(cs.getColumnIndex("dia_chi"));
                String so_dien_thoai = cs.getString(cs.getColumnIndex("so_dien_thoai"));
                String email = cs.getString(cs.getColumnIndex("email"));
                boolean dang_vien = cs.getInt(cs.getColumnIndex("dang_vien")) > 0;
                boolean doan_vien = cs.getInt(cs.getColumnIndex("doan_vien")) > 0;
                boolean cong_doan_vien = cs.getInt(cs.getColumnIndex("cong_doan_vien")) > 0;
                String thanh_tich = cs.getString(cs.getColumnIndex("thanh_tich"));

                CanBo canBo = new CanBo(ma_can_bo, ho_ten, ngay_sinh, gioi_tinh, chuc_danh, chuc_vu, hs_luong, phu_cap_chuc_vu,
                        phu_cap_giang_day, ma_so_thue, so_tai_khoan, dia_chi, so_dien_thoai, email, dang_vien, doan_vien,
                        cong_doan_vien, thanh_tich);

                canBo.setId(canBoList.size() + 1);

                canBoList.add(canBo);
            }
            while (cs.moveToNext());

            adapter = new ItemAdapter(canBoList);
            listView.setAdapter(adapter);

            List<CanBo> duplicateCanBoList = new ArrayList<>(canBoList);
            ac_adapter = new AutoCompleteItemAdapter(this, duplicateCanBoList);
            ac_search.setAdapter(ac_adapter);
        }

    }

    private void openDB() {
        try {
            String path = getBaseContext().getExternalFilesDir("mydb").getPath() + "/db"; //access SD Card
            //String path = getFilesDir().getAbsolutePath() + "/mydb"; //access interal storage
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            //Log.v("TAG", path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createTable() {
        db.beginTransaction();

        try {
            //create table ThongTinCaNhan
            db.execSQL("create table ThongTinCaNhan(" +
                    "ma_can_bo text NOT NULL PRIMARY KEY," +
                    "ho_ten text, "+
                    "ngay_sinh text," +
                    "gioi_tinh text," +
                    "chuc_danh text," +
                    "chuc_vu text," +
                    "hs_luong double," +
                    "phu_cap_chuc_vu double," +
                    "phu_cap_giang_day double," +
                    "ma_so_thue text," +
                    "so_tai_khoan text," +
                    "dia_chi text," +
                    "so_dien_thoai text," +
                    "email text," +
                    "dang_vien int," +
                    "doan_vien int," +
                    "cong_doan_vien int," +
                    "thanh_tich text)"

            );

            //create table GiaDinh
            db.execSQL("create table GiaDinh(" +
                    "ma_can_bo text NOT NULL," +
                    "ho_ten_con text, "+
                    "nam_sinh text," +
                    "thanh_tich text," +
                    "FOREIGN KEY(ma_can_bo) REFERENCES ThongTinCaNhan(ma_can_bo) )"
            );

            //create table NghienCuuKhoaHoc
            db.execSQL("create table NghienCuuKhoaHoc(" +
                    "ma_can_bo text NOT NULL PRIMARY KEY," +
                    "noi_dung text, "+
                    "FOREIGN KEY(ma_can_bo) REFERENCES ThongTinCaNhan(ma_can_bo) )"
            );

            //create table ThongTinGiangDay
            db.execSQL("create table ThongTinGiangDay(" +
                    "ma_can_bo text NOT NULL," +
                    "ma_mon_hoc text NOT NULL, "+
                    "ten_mon text NOT NULL," +
                    "so_tin_chi text NOT NULL," +
                    "ma_lop text NOT NULL," +
                    "so_sinh_vien text NOT NULL," +
                    "hoc_ky text NOT NULL," +
                    "nam_hoc text NOT NULL," +
                    "FOREIGN KEY(ma_can_bo) REFERENCES ThongTinCaNhan(ma_can_bo) )"
            );

            //create table ChamThi
            db.execSQL("create table ChamThi(" +
                    "ma_can_bo text NOT NULL," +
                    "ma_mon_hoc text NOT NULL, "+
                    "ma_lop text NOT NULL," +
                    "ngay_nop text NOT NULL," +
                    "ngay_thu text NOT NULL," +
                    "FOREIGN KEY(ma_can_bo) REFERENCES ThongTinCaNhan(ma_can_bo) )"
            );

            //insert data
            db.execSQL("insert into ThongTinCaNhan(ma_can_bo, ho_ten, ngay_sinh, gioi_tinh, chuc_danh, chuc_vu, " +
                    "hs_luong, phu_cap_chuc_vu, phu_cap_giang_day, ma_so_thue, so_tai_khoan, dia_chi, so_dien_thoai, " +
                    "email, dang_vien, doan_vien, cong_doan_vien, thanh_tich) " +
                    "values('2016001', 'Nguyễn Văn Tuy', '12/05/1975', 'nam', 'Tiến sĩ', 'giảng viên', '0.7', " +
                    "'1.2', '1.5', '12345', '987654321', 'Thanh Xuan, Ha Noi', '012345678', 'tuy@soict.hust.edu.vn', '1', '0', '1'," +
                    "'-Danh hiệu gv giỏi năm 2015\n-Danh hiệu gv giỏi năm 2016')"
            );

            db.execSQL("insert into ThongTinCaNhan(ma_can_bo, ho_ten, ngay_sinh, gioi_tinh, chuc_danh, chuc_vu, " +
                    "hs_luong, phu_cap_chuc_vu, phu_cap_giang_day, ma_so_thue, so_tai_khoan, dia_chi, so_dien_thoai, " +
                    "email, dang_vien, doan_vien, cong_doan_vien, thanh_tich) " +
                    "values('2016002', 'Lê Thị Giang', '12/05/1978', 'nữ', 'Tiến sĩ', 'giảng viên', '0.7', " +
                    "'1.7', '1.8', '12345', '987654321', 'Thanh Xuan, Ha Noi', '012345678', 'tuy@soict.hust.edu.vn', '1', '0', '1'," +
                    "'-Danh hiệu chiến sĩ thi đua năm 2015\n-Danh hiệu gv giỏi năm 2018')"
            );

            db.execSQL("insert into GiaDinh(ma_can_bo, ho_ten_con, nam_sinh, thanh_tich) " +
                    "values('2016001', 'Nguyễn Văn Hoàng', '2005', 'Học sinh giỏi lớp 5')"
            );

            db.execSQL("insert into GiaDinh(ma_can_bo, ho_ten_con, nam_sinh, thanh_tich)" +
                    "values('2016001', 'Nguyễn Văn Anh', '2003', 'Học sinh giỏi lớp 7')"
            );

            db.execSQL("insert into GiaDinh(ma_can_bo, ho_ten_con, nam_sinh, thanh_tich)" +
                    "values('2016002', 'Nguyễn Cao Hoàng An', '2003', 'Học sinh giỏi lớp 8')"
            );

            db.execSQL("insert into GiaDinh(ma_can_bo, ho_ten_con, nam_sinh, thanh_tich)" +
                    "values('2016002', 'Nguyễn Thị Cẩm Vân', '2000', 'Đỗ Đại học')"
            );

            db.setTransactionSuccessful();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
    public void connectToServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connect(new InetSocketAddress(address, 1100), 5000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private class SendFileToServer extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String connectStatus = "notconnect";
            try {
                if (client.isConnected() && !client.isClosed()) {
                    // Connect success
                    connectStatus = "connectting";
                    String path = getBaseContext().getExternalFilesDir("mydb").getPath() + "/db";
                    File file = new File(path);
                    if (file.exists()) {
                        String fileSize = Long.toString(file.length());
                        String header = "db*" + fileSize;
                        OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
                        byte buffer[] = new byte[1024];
                        int byteRead = 0;
                        InputStream is =  client.getInputStream();

                        writer.write("1000");
                        writer.flush(); // Send request to send file

                        String responseFromServer = "";
                        int bytesRead = is.read(buffer, 0, buffer.length);
                        responseFromServer = new String(buffer, 0, bytesRead);

                        if (responseFromServer.equals("readytoreceive")) {
                            Log.v("TAG", "start send");
                            writer.write(header);
                            writer.flush();

                            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
                            OutputStream os = client.getOutputStream();

                            while ( (reader.read(buffer, 0, buffer.length) ) > 0) {
                                byteRead += buffer.length;
                                //Log.v("TAG", String.valueOf(byteRead));
                                os.write(buffer, 0, buffer.length);
                                os.flush();
                            }
                            reader.close();
                        }

                    } else {
                        Log.v("TAG", "File is not existed");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress(connectStatus);

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (values[0].equals("notconnect")) {
                Toast.makeText(MainActivity.this, "Connection Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class RecieveDataFromServer extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String connectStatus = "notconnect";
            try {
                if (client.isConnected() && !client.isClosed()) {
                    // Connect success
                    connectStatus = "connectting";
                    byte buffer[] = new byte[1024];
                    InputStream is =  client.getInputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
                    writer.write("2000");
                    writer.flush(); // Send request to receive file

                    String responseFromServer = "";
                    int bytesResponse = is.read(buffer, 0, buffer.length);
                    responseFromServer = new String(buffer, 0, bytesResponse);

                    if (responseFromServer.equals("readytosend")) {
                        Log.v("TAG", "start receive");
                        FileOutputStream fos = null;
                        BufferedOutputStream bos = null;
                        int bytesRead;
                        long size = 0;
                        long fileSize = 0;
                        String fileName = "";
                        String result = "";

                        while ( (bytesRead = is.read(buffer, 0, buffer.length) ) > 0) {
                            size += bytesRead;

                            result = new String(buffer, 0, bytesRead);
                            publishProgress(result);

                            //paser buffer to get fileName and fileSize
                            if (fileSize <= 0) {
                                int position = result.indexOf('*');
                                int stringLength = result.length();
                                fileName = result.substring(0, position);

                                //Log.v("TAG", "file name: " + fileName);
                                //Log.v("TAG", "file size: " + result.substring(position + 1, stringLength));

                                String path = getBaseContext().getExternalFilesDir("mydb").getPath(); //access SD Card
                                File file = new File(path + "/db");

                                fos =  new FileOutputStream(file);
                                bos = new BufferedOutputStream(fos);
                                try {
                                    fileSize = Long.parseLong(result.substring(position + 1,stringLength));
                                    Log.v("TAG", "size bytes is: " + Long.toString(fileSize));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                // write in file
                                bos.write(buffer, 0, bytesRead);

                                if (size >= fileSize ) {
                                    //Log.v("TAG", "size bytes: " + Long.toString(size));
                                    bos.close();
                                    break;
                                }
                            }
                        }
                        //Log.v("TAG", "receive done" );
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            publishProgress(connectStatus);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (values[0].equals("notconnect")) {
                Toast.makeText(MainActivity.this, "Connection Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_update_from_server) {
            Log.v("TAG", "update action");
            new RecieveDataFromServer().execute();
        } else if (id == R.id.action_upload_to_server) {
            Log.v("TAG", "upload to server");
            new SendFileToServer().execute();

        } else if (id == R.id.action_add_person) {
            Log.v("TAG", "add CanBo");
            customDialog.showCustomDialogEdit(true,null, null);

        } else if (id == R.id.action_export_report) {
            Log.v("TAG", "action export");
            customDialog.showCustomDialogExportOptions(canBoList);

        } else if (id == R.id.action_refresh) {
            Log.v("TAG", "refresh");
            displayListView();
        }

        return super.onOptionsItemSelected(item);
    }
}
