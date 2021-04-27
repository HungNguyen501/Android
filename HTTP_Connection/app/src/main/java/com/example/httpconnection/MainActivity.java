package com.example.httpconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btnParseJson;
    Button btnWrapJson;
    public String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_content);
        btnParseJson = findViewById(R.id.btn_parse_json);
        btnWrapJson = findViewById(R.id.btn_wrap_to_json);

        new GetMethod().execute();
        //new PostMethod().execute();

        btnParseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                {
                    "id": 1,
                    "name": "Leanne Graham",
                    "username": "Bret",
                    "email": "Sincere@april.biz",
                    "address": {
                        "street": "Kulas Light",
                        "suite": "Apt. 556",
                        "city": "Gwenborough",
                        "zipcode": "92998-3874",
                        "geo": {
                            "lat": "-37.3159",
                            "lng": "81.1496"
                        }
                    },
                    "phone": "1-770-736-8031 x56442",
                    "website": "hildegard.org",
                    "company": {
                        "name": "Romaguera-Crona",
                        "catchPhrase": "Multi-layered client-server neural-net",
                        "bs": "harness real-time e-markets"
                    }
                }
                */

                try {
                    //Log.v("TAG", "jsonString: " + jsonString);
                    JSONArray jArr = new JSONArray(jsonString);
                    JSONObject jObj = jArr.getJSONObject(0);
                    JSONObject addressObj = jObj.getJSONObject("address");
                    JSONObject companyObj = jObj.getJSONObject("company");

                    String id = jObj.getString("id");
                    String name = jObj.getString("name");
                    String street = addressObj.getString("street");
                    String catchPhrase = companyObj.getString("catchPhrase");

                    Log.v("TAG","Number of objects: " + jArr.length());
                    Log.v("TAG","id: " + id + " |name: " + name + " |street: " + street + " |catchPhrase: " + catchPhrase);

                    int debug = 0;

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        final List<JsonModel> list_model = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Geo geo = new Geo();
            geo.lat = Integer.toString(i);
            geo.lng = Integer.toString(i);

            Address address = new Address();
            address.city = "hanoi" + Integer.toString(i);
            address.geo = geo;
            address.street = "hang trong " + Integer.toString(i);
            address.suite = "suite" + Integer.toString(i);
            address.zipcode = Integer.toString(i);

            Company company = new Company();
            company.bs =  Integer.toString(i);
            company.catchPhrase = Integer.toString(i);
            company.name = "name" + Integer.toString(i);

            list_model.add(new JsonModel(Integer.toString(i), "hung" + Integer.toString(i), "user" + Integer.toString(i),
                    "user" + Integer.toString(i) + "@gmail.com", address, "123", "1.com", company));
        }

        btnWrapJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < 2; i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", list_model.get(i).getId());
                        jsonObject.put("name", list_model.get(i).getName());
                        jsonObject.put("city", list_model.get(i).getAddress().city);
                        jsonArray.put(jsonObject);
                    }

                    String jsonString = jsonArray.toString();
                    Log.v("TAG", jsonString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private class GetMethod extends AsyncTask<Void, Void, Void> {

        GetMethod() {
            //constructor is here
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //textView.setText("start");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/users");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                // optional default is GET
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                Log.v("TAG", "Sending 'GET' request to URL : " + url.toString());
                Log.v("TAG", "Response Code : " + responseCode);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(con.getInputStream()));
                StringBuilder response;
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print result
                jsonString = response.toString();
                Log.v("TAG", jsonString);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... voids) {
            super.onProgressUpdate(voids);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText(jsonString);

        }
    }

    private class DownloadFile extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new
                        URL("https://data.chiasenhac.com/dataxx/16/downloads/1021/2/1020691- 46ae8508/128/Happy%20New%20Year%20-%20ABBA.mp3");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                Log.v("TAG", "Response Code = " + responseCode);
                InputStream inputStream = conn.getInputStream();
                File file = new File(MainActivity.this.getFilesDir(), "test.mp3");
                FileOutputStream outputStream = openFileOutput("test.mp3", MODE_PRIVATE);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1)
                    outputStream.write(buffer, 0, len);
                outputStream.close();
                inputStream.close();
                Log.v("TAG", "Download complete");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }

    private class PostMethod extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://httpbin.org/post");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //add reuqest header
                con.setRequestMethod("POST");
                String params = "user=admin&pass=123456";
                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(params);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                Log.v("TAG", "Sending 'POST' request to URL : " + url.toString());
                Log.v("TAG", "Response Code : " + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //print result
                Log.v("TAG", response.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }




    }

}
