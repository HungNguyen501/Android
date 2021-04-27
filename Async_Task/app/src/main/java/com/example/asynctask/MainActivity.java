package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnAsynTask;
    Long startingMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittext);
        btnAsynTask = findViewById(R.id.btn_asynstask);

        btnAsynTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new excuteAsynTask().execute("dummy1", "dummy2");
            }
        });

    }

    private  class excuteAsynTask extends AsyncTask<String, String, Void> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        String waitMsg = "Wait \nSome slow job is being done... ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startingMillis = System.currentTimeMillis();
            editText.setText("Start time: " + startingMillis);
            this.dialog.setMessage(waitMsg);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            // show on Log.e the supplied dummy arguments
            Log.e("TAG", "doInBackground>>Total args: " + strings.length );
            Log.e("TAG", "doInBackground>> strings[0] = " + strings[0] );
            try {
                for (Long i = 0L; i < 5L; i++) {
                    Thread.sleep(1000);
                    publishProgress( Long.toString(i), "this is second string" ); //update in onProgressUpdate void
                }
            } catch (InterruptedException e) {
                Log.e("slow-job interrupted", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.e("TAG", "onProgressUpdate >> values[0]: " + values[0]);
            Log.e("TAG", "onProgressUpdate >> values[1]: " + values[1]);

            dialog.setMessage(waitMsg + values[0]);
            editText.append("\nworking..." + values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            // cleaning-up, all done
            editText.append("\nEnd Time:"
                    + (System.currentTimeMillis() - startingMillis) / 1000);
            editText.append("\ndone!");

        }



    }
}
