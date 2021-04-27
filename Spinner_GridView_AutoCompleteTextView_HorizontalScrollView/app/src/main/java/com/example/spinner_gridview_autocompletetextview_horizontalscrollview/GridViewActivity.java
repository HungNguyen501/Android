package com.example.spinner_gridview_autocompletetextview_horizontalscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    Integer items[] = {R.drawable.thumb1, R.drawable.thumb2, R.drawable.thumb3,
            R.drawable.thumb4, R.drawable.thumb5, R.drawable.thumb6,
            R.drawable.thumb7, R.drawable.thumb8, R.drawable.thumb9};
    ArrayAdapter<Integer> adapter;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        adapter = new ArrayAdapter<Integer>(
                GridViewActivity.this,
                android.R.layout.simple_list_item_1,
                items);

        gridView = findViewById(R.id.grid_view);
        ImageAdapter adapter = new ImageAdapter(items, this);
        gridView.setAdapter(adapter);
    }
}
