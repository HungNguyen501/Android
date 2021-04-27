package com.example.sql_databases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        openDB();
        createTable();

        findViewById(R.id.btn_load_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDB();
            }
        });

    }

    private void openDB() {
        try {
            //String SDcardPath = Environment .getExternalStorageDirectory() .getPath() + "/mydb"; //access SD Card
            String path = getFilesDir() + "/mydb";
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createTable() {
        db.beginTransaction();

        try {
            //create table and fields
            db.execSQL("create table tblAMIGO(" +
                    "ID integer PRIMARY KEY autoincrement," +
                    "name text," +
                    "phone text)");

            //insert data
            db.execSQL("insert into tblAMIGO(name, phone) values('A', '555-111')");
            db.execSQL("insert into tblAMIGO(name, phone) values('B', '555-2222')");
            db.execSQL("insert into tblAMIGO(name, phone) values('C', '555-2222')");

            db.setTransactionSuccessful();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private void selectDB() {
        String sql = "select * from tblAMIGO";
        Cursor cs = db.rawQuery(sql, null);

        cs.moveToFirst();

        String content = "";
        content += ( "      " + cs.getColumnName(0) + "   " + cs.getColumnName(1) + "    " + cs.getColumnName(2) + "\n" );

        do {
            int ID = cs.getInt(0);
            String name = cs.getString(cs.getColumnIndex("name"));
            String phone = cs.getString(2);
            content += ( "      " + Integer.toString(ID) + "     " + name + "           " + phone + "\n" );
        }
        while (cs.moveToNext());

        textView.setText(content);

    }

}
