package com.example.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textUri;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action dial
        findViewById(R.id.btn_dial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myPhoneNumber = "tel:555-1234";
                Intent myAct2 = new Intent(Intent.ACTION_DIAL, Uri.parse(myPhoneNumber));
                startActivity(myAct2);
            }
        });

        //action call
        findViewById(R.id.btn_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myPhoneNumber = "tel:555-1234";
                Intent myAct2 = new Intent(Intent.ACTION_CALL, Uri.parse(myPhoneNumber));
                startActivity(myAct2);
            }
        });

        //action web search
        findViewById(R.id.btn_web_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Intent.ACTION_WEB_SEARCH);
                myintent.putExtra(SearchManager.QUERY, "mua nguoi yeu");
                startActivity(myintent);
            }
        });

        //action send sms
        findViewById(R.id.btn_send_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:555-4321"));
                myintent.putExtra("sms_body", "yeu anh khong");
                startActivity(myintent);
            }
        });

        //action get content
        findViewById(R.id.btn_get_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent();
                myintent.setType("image/pictures/*");
                myintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(myintent);
            }
        });

        //action view contact, edit contact
        findViewById(R.id.btn_view_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //view contacts
                /*
                String data = "content://contacts/people/";
                Intent myintent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                startActivity(myintent);

                 */

                //edit contacts
                String data = ContactsContract.Contacts.CONTENT_URI + "/" + "2"; //2 is id of contact in contacts list
                Intent myAct2 = new Intent(Intent.ACTION_EDIT, Uri.parse(data));
                startActivity(myAct2);
            }
        });

        //action view youtube
        findViewById(R.id.btn_view_youtube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUriString = "http://wwww.youtube.com";
                Intent myAct2 = new Intent(Intent.ACTION_VIEW, Uri.parse(myUriString));
                startActivity(myAct2);
            }
        });

        //action view map
        findViewById(R.id.btn_view_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to the specific location
                /*
                String thePlace = "Cleveland State University, Ohio";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=(" + thePlace + ")"));
                startActivity(intent);
                */

                // map is centered aroung given Lat, Long
                /*
                String geoCode = "geo:41.5020952,-81.6789717&z=16";
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
                startActivity(intent2);
                 */

                //get driving direction
                String drirection = "http://maps.google.com/maps?" + "saddr=9.938083,-84.054430&" + "daddr=9.926392,-84.055964";
                Intent intent3 = new Intent( android.content.Intent.ACTION_VIEW, Uri.parse( drirection));
                startActivity(intent3);

            }
        });

        //action send email
        findViewById(R.id.btn_view_youtube).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailSubject = "Department Meeting";
                String emailText = "We’ll discuss the new project " + "on Tue. at 9:00am @ room BU344";
                String[] emailReceiverList = {"v.matos@csuohio.edu"};

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("vnd.android.cursor.dir/email");
                intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailText);
                startActivity(Intent.createChooser(intent, "To complete action choose:"));

            }
        });

        //action pick contact
        findViewById(R.id.btn_pick_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(myintent, 1234);
            }
        });

        //action view detail contact
        findViewById(R.id.btn_view_detail_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(textUri.getText().toString());
                Intent myintent  = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(myintent);
            }
        });

        textUri = findViewById(R.id.text_uri);
        textResult = findViewById(R.id.text_result);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit1 = findViewById(R.id.edt_view_1);
                String val1 = edit1.getText().toString();

                EditText edit2 = findViewById(R.id.edt_view_2);
                String val2 = edit2.getText().toString();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("val1", val1);
                intent.putExtra("val2", val2);

                startActivityForResult(intent, 6789);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                textUri.setText(data.getDataString());
            }
        } else if (requestCode == 6789) {
            if (requestCode == Activity.RESULT_OK) {
                String s = data.getStringExtra("result");
                textResult.setText("Result: " + s);
            } else {
                String msg = data.getStringExtra("msg");
                textResult.setText("Result: " + msg);
            }
        }

    }

}
