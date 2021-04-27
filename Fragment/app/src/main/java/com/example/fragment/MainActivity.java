package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RedFragment redFragment;
    BlueFragment blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redFragment = new RedFragment();
        blueFragment = BlueFragment.newInstance("Hello Blue Fragment", "Hello everybody");

        findViewById(R.id.btn_add_red_ft).setOnClickListener(this);
        findViewById(R.id.btn_add_blue_ft).setOnClickListener(this);
        findViewById(R.id.btn_replace_blue_ft).setOnClickListener(this);
        findViewById(R.id.btn_remove_blue_ft).setOnClickListener(this);

        findViewById(R.id.btn_second_activity).setOnClickListener(this);
        findViewById(R.id.btn_third_activity).setOnClickListener(this);
        findViewById(R.id.btn_fourth_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btn_add_red_ft) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.ft_layout_red, redFragment);
            ft.commit();
        } else if (id == R.id.btn_add_blue_ft) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.ft_layout_blue, blueFragment);
            ft.commit();
        } else if (id == R.id.btn_replace_blue_ft) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.ft_layout_blue, blueFragment);
            ft.commit();
        } else if (id == R.id.btn_remove_blue_ft) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(blueFragment);
            ft.commit();
        }else if (id == R.id.btn_second_activity) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_third_activity) {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_fourth_activity) {
            Intent intent = new Intent(MainActivity.this, FourthActivity.class);
            startActivity(intent);
        }
    }
}
