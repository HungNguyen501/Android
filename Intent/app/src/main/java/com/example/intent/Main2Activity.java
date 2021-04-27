package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String val1 = intent.getStringExtra("val1");
        String val2 = intent.getStringExtra("val2");

        try {
            //double d1 = Double.parseDouble(val1);
            //double d2 = Double.parseDouble(val2);
            String s = val1 + val2;

            TextView textView = findViewById(R.id.text_view);
            textView.setText("val1: " + val1 + "\nval2: " + val2 + "\nresult: " + s);

            intent.putExtra("result", s);

            setResult(Activity.RESULT_OK, intent);
        } catch (Exception ex) {
            intent.putExtra("msg", ex.getMessage());
            setResult(Activity.RESULT_CANCELED, intent);
        }


        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
