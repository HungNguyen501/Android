package com.example.listbaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        items = new ArrayList<>();
        for(int i = 0; i < 20; i++)
            items.add("Item " + (i + 1) );

        adapter = new ArrayAdapter<String>(
                this,
                R.layout.layout_item,
                R.id.label_item,
                items);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, items.get(position) + " is selected", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 5; i++)
                    items.add("New Item " + (i + 1) );
                adapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 10; i++)
                    if(items.size() > 0)
                        items.remove(0);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
