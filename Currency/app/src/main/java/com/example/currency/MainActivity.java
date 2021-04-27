package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] currency_type = {"Vietnam - Dong", "United States - Dollar", "Japan - Yen", "Korea - Won", "Europe - Euro"};
    String[] currency_sign = {"VND", "USD", "JPY", "KRW", "EURO"};
    double[] toVND = { 1, 23430.18, 217.39, 19.18, 25471.22 };
    ArrayAdapter adapter;
    Spinner spinner1, spinner2;
    TextView[] textView = new TextView[3];
    int[] cur_mode = new int[3];
    /*
    cur_mode[0]: change content of textview[0] or textview[1]
    cur_mode[1]: which currency wanna convert
    cur_mode[2]: which currency is ouput
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cur_mode[0] = 0;
        cur_mode[1] = 0;
        cur_mode[2] = 0;

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_single_choice,
                currency_type
        );

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (cur_mode[0] == 0) {
                    cur_mode[1] = position;
                } else {
                    cur_mode[2] = position;
                }
                setText_Display_Exc_Value();
                calculTextView(-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (cur_mode[0] == 1) {
                    cur_mode[1] = position;
                } else {
                    cur_mode[2] = position;
                }
                setText_Display_Exc_Value();
                calculTextView(-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textView[0] = findViewById(R.id.text_view_1);
        textView[1] = findViewById(R.id.text_view_2);
        textView[2] = findViewById(R.id.convert_value_text_view);

        setText_Display_Exc_Value();

        textView[0].setOnClickListener(this);
        textView[1].setOnClickListener(this);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);

    }

    public void setText_Display_Exc_Value() {
        String temp = "1 ";
        temp += ( currency_sign[cur_mode[1]] +  " = ");
        temp += String.format( "%.5f", toVND[cur_mode[1]] / toVND[cur_mode[2]] );
        temp += ( " " + currency_sign[cur_mode[2]]);

        textView[2].setText(temp);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void calculTextView(int number) {
        String text1;
        int  exc_currency;
        double des_currency;

        text1 = textView[cur_mode[0]].getText().toString();

        try {
            int debug = Integer.parseInt(text1);
        } catch(Exception e) {
            text1 = "0";
        }

        if(text1.length() <= 8 && number != -1) // dont append number when click spinner to convert to another currency
        {
            text1 += Integer.toString(number);
        }

        exc_currency = Integer.parseInt(text1);
        des_currency = toVND[cur_mode[1]] / toVND[cur_mode[2]] * exc_currency;

        textView[cur_mode[0]].setText(Integer.toString(exc_currency));
        if (cur_mode[0] == 0)
            textView[1].setText(String.format ("%.2f", des_currency));
        else
            textView[0].setText(String.format ("%.2f", des_currency));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_1:
                if (cur_mode[0] == 1) {
                    cur_mode[0] = 0;
                    //swap 2 cur_mode[1] and cur_mode[2]
                    int temp_cur = cur_mode[1];
                    cur_mode[1] = cur_mode[2];
                    cur_mode[2] = temp_cur;
                    setText_Display_Exc_Value();
                }
                textView[0].setTypeface(null, Typeface.BOLD);
                textView[1].setTypeface(null, Typeface.NORMAL);
                break;
            case R.id.text_view_2:
                if (cur_mode[0] == 0) {
                    cur_mode[0] = 1;
                    //swap 2 cur_mode[1] and cur_mode[2]
                    int temp_cur = cur_mode[1];
                    cur_mode[1] = cur_mode[2];
                    cur_mode[2] = temp_cur;
                    setText_Display_Exc_Value();
                }
                textView[1].setTypeface(null, Typeface.BOLD);
                textView[0].setTypeface(null, Typeface.NORMAL);
                break;
            case R.id.btn_ce:
                textView[0].setText("0");
                textView[1].setText("0");
                calculTextView(0);
                break;
            case R.id.btn_del:
                String temp_text = null;
                temp_text = textView[cur_mode[0]].getText().toString();
                if (temp_text.length() > 1 )
                    temp_text = temp_text.substring(0, temp_text.length() - 1);
                else {
                    temp_text = "0";
                }
                textView[cur_mode[0]].setText(temp_text);
                calculTextView(-1);
                break;
            case R.id.btn_0:
                calculTextView(0);
                break;
            case R.id.btn_1:
                calculTextView(1);
                break;
            case R.id.btn_2:
                calculTextView(2);
                break;
            case R.id.btn_3:
                calculTextView(3);
                break;
            case R.id.btn_4:
                calculTextView(4);
                break;
            case R.id.btn_5:
                calculTextView(5);
                break;
            case R.id.btn_6:
                calculTextView(6);
                break;
            case R.id.btn_7:
                calculTextView(7);
                break;
            case R.id.btn_8:
                calculTextView(8);
                break;
            case R.id.btn_9:
                calculTextView(9);
                break;
            case R.id.btn_dot:

                break;
        }
    }
}
