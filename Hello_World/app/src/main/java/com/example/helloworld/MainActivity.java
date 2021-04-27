package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    RelativeLayout layoutmain;
    EditText editColor;
    String color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("TAG", "onCreate");
        setContentView(R.layout.activity_main);

        /*layoutmain = findViewById(R.id.layout_main);
        textview = findViewById(R.id.text_hello);
        editColor = findViewById(R.id.edit_color);

        textview.setTextSize(20);
        textview.setTextColor(Color.parseColor("green"));
        //textview.setTypeface();
        textview.setTypeface(null, Typeface.ITALIC);

        editColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                color = s.toString();
                try {
                    layoutmain.setBackgroundColor(Color.parseColor(color));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText(R.string.test);

                Log.v("TAG", "Nut da duoc nhan");
            }
        });

        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //onPause() -> onStop() -> onDestroy()
            }
        });

        Log.v("TAG", "Chuong trinh da khoi dong xong");*/


        // Upcasting
        Person p1 = new Manager();
        p1.getDetail();

        // Downcasting
        Employee e1 = new Employee();
        Manager m1 = (Manager) e1;
        m1.calculateSalary();

    }
	
	/*Cach 2 de luu tru trang thai cua app bang OnSaveInstanceState 
	va onRestoreInstanceState*/

    /*

	@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("COLOR", color);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        color = savedInstanceState.getString("COLOR");

        editColor.setText(color);
    }
	
	*/

    /*@Override
    protected void onStart() {
        super.onStart();
        Log.v("TAG", "onStart");

        SharedPreferences prefs = getSharedPreferences("COLORS", MODE_PRIVATE);
        String color = prefs.getString("BACKGROUND_COLOR", "");
        editColor.setText(color);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("TAG", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause(); //luu tru trang thai, cac bien ta dang su dung trong activity hien tai
        Log.v("TAG", "onPause");

        SharedPreferences prefs = getSharedPreferences("COLORS", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("BACKGROUND_COLOR", editColor.getText().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("TAG", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("TAG", "onDestroy");
    }*/

}
