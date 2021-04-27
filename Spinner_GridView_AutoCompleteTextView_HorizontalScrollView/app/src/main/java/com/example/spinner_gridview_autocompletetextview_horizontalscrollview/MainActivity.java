package com.example.spinner_gridview_autocompletetextview_horizontalscrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;
    String words[] = {"one", "two", "three", "four",
                    "five", "six", "seven",
                    "eight", "nine", "ten"};
    ArrayAdapter<String> adapter, adapter2;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //click button to go to GridView
        findViewById(R.id.btn_grid_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_hs_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HorizontalScrollViewActivity.class);
                startActivity(intent);
            }
        });

        items = new ArrayList<String>();
        for(int i = 0; i < 20; i++)
            items.add("Item " + ( i+ 1) );

        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                items);

        adapter2 = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_single_choice,
                words);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(4); // set selected item to default
        spinner.getSelectedItemPosition(); //get position of selected item

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, items.get(position) + " is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.ac_text_view);
        autoCompleteTextView.setAdapter(adapter2);

    }
}
