package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity implements MasterFragment.updateDetailListener{

    MasterFragment masterFragment;
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        masterFragment = new MasterFragment();
        detailFragment = new DetailFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.list_ft, masterFragment);
        ft.replace(R.id.detail_ft, detailFragment);
        ft.commit();

    }

    @Override
    public void updateDetail(String selectedItem) {
        detailFragment.updateDetai(selectedItem);
    }
}
