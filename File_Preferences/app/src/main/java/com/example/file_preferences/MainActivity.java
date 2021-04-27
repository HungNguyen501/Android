package com.example.file_preferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final String SETTINGS_FILE = "settings";
    private final String NAME_SETTING = "name";

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);

        findViewById(R.id.btn_shared_preferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences(SETTINGS_FILE, 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("string", "shared preferences");
                editor.commit();

                //retrieving data from Shared Preferences container
                String s = prefs.getString("string", "no value");
                editText.setText(s);
            }
        });

        findViewById(R.id.btn_write_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23)
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Log.v("TAG", "Permission denied! Asking for permission from user");
                        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1234);
                    }

                try {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath(); //access SD Card
                    //String path = getFilesDir().getAbsolutePath(); //access Interal Storage
                    File file = new File(path + "/settings.txt");

                    Log.v("TAG", file.getPath());

                    OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
                    writer.write(editText.getText().toString());
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_read_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23)
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Log.v("TAG", "Permission denied! Asking for permission from user");
                        requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 5678);
                    }

                String path = Environment.getExternalStorageDirectory().getAbsolutePath(); //access SD Card
                //String path = getFilesDir().getAbsolutePath(); //access Interal Storage
                File file = new File(path + "/settings.txt");

                if (file.exists()) {
                    try {
                        InputStream is = new FileInputStream(file);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                        String result = "";
                        String line;

                        while ((line = reader.readLine()) != null)
                            result += line + "\n";

                        reader.close();
                        is.close();

                        editText.setText(result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else
                    Log.v("TAG", "File khong ton tai");
            }
        });

        findViewById(R.id.btn_delete_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getFilesDir() + "/settings.txt");
                if (file.exists()) {
                    Log.v("TAG", "File ton tai");
                    file.delete();
                } else
                    Log.v("TAG", "File khong ton tai");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1234) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Log.v("TAG", "Permission granted");
            else
                Log.v("TAG", "Permission denied");
        }
    }

}


