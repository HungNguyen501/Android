package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class FourthActivity extends AppCompatActivity implements View.OnClickListener{
    int index_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        index_fragment = 0;

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_replace).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_pop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();

        if (id == R.id.btn_add) {
            index_fragment++;

            BlueFragment blueFragment = BlueFragment.newInstance(String.valueOf(index_fragment), "");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_layout, blueFragment, "BLUE-TAG");
            ft.addToBackStack("BLUE"); // add to Stack
            ft.commit();
        } else if (id == R.id.btn_replace) {
            index_fragment++;

            BlueFragment blueFragment = BlueFragment.newInstance(String.valueOf(index_fragment), "");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_layout, blueFragment, "BLUE-TAG");
            ft.addToBackStack("BLUE"); // add to Stack
            ft.commit();
        } else if (id == R.id.btn_pop) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack();
        } else if (id == R.id.btn_remove) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag("BLUE-TAG");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }

    }
}
