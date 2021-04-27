package com.example.menu_actionbar_dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Action Bar");
        actionBar.setSubtitle("Examples");

        actionBar.setIcon(R.mipmap.ic_launcher); //display icon of actionbar
        //actionBar.setDisplayShowHomeEnabled(true); //display tittle, subtittle of actionbar
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_color_actionbar));

        actionBar.setDisplayShowCustomEnabled(true); //enable to show spinner for actionbar
        actionBar.setCustomView(R.layout.custom_view_spinner);

        Spinner spinner = actionBar.getCustomView().findViewById(R.id.custom_view_spinner);

        items = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            items.add("Item " + (i + 1) );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items);

        spinner.setAdapter(adapter);

        //setup listview
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setLongClickable(true);
        registerForContextMenu(listView); //register to show popup menu when long tap listview

        findViewById(R.id.btn_show_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, findViewById(R.id.btn_show_popup));
                popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        findViewById(R.id.btn_show_alert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                AlertDialog dialog = builder.setTitle("Quit Application")
                        .setMessage("Are you sure you want to quit?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

    }

    private void showCustomDialog() {
        final Dialog customDialog = new Dialog(MainActivity.this);
        customDialog.setContentView(R.layout.custom_dialog);
        TextView textView = customDialog.findViewById(R.id.text_view);
        textView.setText("Enter your name:");

        final EditText editText = customDialog.findViewById(R.id.edit_text);

        customDialog.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                customDialog.dismiss();
            }
        });
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();

        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); //set width, height for dialog
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Select an action");
        menu.add(0, 0, 0, "Call");
        menu.add(0, 1, 0, "SMS");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.v("TAG", items.get(info.position) + " selected");

        int id = item.getItemId();
        if (id == 0) // CALL
            Log.v("TAG", "CALL action");
        else if (id == 1) // SMS
            Log.v("TAG", "Send SMS action");

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main, menu);

       SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               Log.v("TAG", "Searching for" + query);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return false;
           }
       });

       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Log.v("TAG", "share");
        } else if (id == R.id.action_download) {
            Log.v("TAG", "download");
        } else if (id == R.id.action_settings) {
            Log.v("TAG", "settings");
        } else if (id == R.id.action_about) {
            Log.v("TAG", "about");
        }

        return super.onOptionsItemSelected(item);
    }
}
