package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity implements MasterFragment.updateDetailListener {
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getId() == R.id.fragment_detail)
            detailFragment = (DetailFragment) fragment;

    }

    @Override
    public void updateDetail(String selectedItem) {
        detailFragment.updateDetai(selectedItem);
    }
}
