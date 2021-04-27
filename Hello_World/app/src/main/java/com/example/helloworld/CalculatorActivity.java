package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textview_screen;
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
    Button btn_c, btn_ce, btn_bs;
    Button btn_divide, btn_plus, btn_subtract, btn_multiple, btn_dot, btn_equal, btn_sign;
    int[] numbers = new int[3];
    int cal_sign = 0; //0: none, 1: plus, 2: substract, 3: multiple, 4: divide
    int max_length = 9;
    int cur_mode = 0;
    int temp2 = 0;
    int temp_cal_sign = 0;
    boolean tap_eqal_again = false, reset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        numbers[0] = 0;
        numbers[1] = 0;
        numbers[2] = 0;

        textview_screen = findViewById(R.id.textview_screen);
        Typeface tf = Typeface.createFromAsset(getAssets(), "DS-DIGI.TTF");
        textview_screen.setTypeface(tf);

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
        findViewById(R.id.btn_c).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_bs).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiple).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_subtract).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_sign).setOnClickListener(this);

    }

    public  void resetVars() {
        numbers[0] = 0;
        numbers[1] = 0;
        numbers[2] = 0;
        cur_mode = 0;
        cal_sign = 0;
        temp2 = 0;
        reset = false;
        tap_eqal_again = false;
        textview_screen.setText(Integer.toString(numbers[cur_mode]));
    }

    @SuppressLint("SetTextI18n")
    public void calculate(int cal_sign, int b) {
        if (cal_sign == 1) {
            numbers[0] += b;
        } else  if(cal_sign == 2) {
            numbers[0] -= b;
        } else  if(cal_sign == 3) {
            numbers[0] *= b;
        } else  if(cal_sign == 4 && b != 0) {
                numbers[0]  /= b;
        }
        cur_mode = 0;
        numbers[1] = 0;
        if(Integer.toString(numbers[0]).length() > max_length || (b == 0 && cal_sign == 4)) {
            numbers[0] = 0;
            textview_screen.setText("Overflow");
        } else
            textview_screen.setText(Integer.toString(numbers[0]));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String temp;
        switch (view.getId()) {
            case R.id.btn_c:
                resetVars();
                break;
            case R.id.btn_ce:
                if(cur_mode == 0 || numbers[1] == 0) {
                    resetVars();
                } else {
                    numbers[1] = 0;
                    textview_screen.setText("0");
                }
                break;
            case R.id.btn_bs:
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() > 1 ) {
                    temp = temp.substring(0, temp.length() - 1);
                } else
                    temp = "0";
                try {
                    numbers[cur_mode] = Integer.parseInt(temp);
                } catch (Exception e) {
                    numbers[cur_mode] = 0;
                }
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_0:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "0";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_1:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "1";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_2:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "2";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_3:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "3";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_4:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "4";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_5:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "5";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_6:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "6";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_7:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "7";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_8:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "8";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_9:
                if(reset && cur_mode == 0) resetVars();
                temp = Integer.toString((numbers[cur_mode]));
                if (temp.length() < max_length) {
                    temp += "9";
                }
                numbers[cur_mode] = Integer.parseInt(temp);
                temp = Integer.toString((numbers[cur_mode]));
                textview_screen.setText(temp);
                break;
            case R.id.btn_plus:
                tap_eqal_again = false;
                if(cal_sign != 0) {
                    calculate(cal_sign, numbers[1]);
                    cal_sign = 1;
                } else {
                    cal_sign = 1;
                    textview_screen.setText("+");
                    if (cur_mode == 0) cur_mode++;
                }
                numbers[1] = 0;
                break;
            case R.id.btn_subtract:
                tap_eqal_again = false;
                if(cal_sign != 0) {
                    calculate(cal_sign, numbers[1]);
                    cal_sign = 2;
                } else {
                    cal_sign = 2;
                    textview_screen.setText("-");

                    if (cur_mode == 0) cur_mode++;
                }
                break;
            case R.id.btn_multiple:
                tap_eqal_again = false;
                if(cal_sign != 0) {
                    calculate(cal_sign, numbers[1]);
                    cal_sign = 3;
                } else {
                    cal_sign = 3;
                    textview_screen.setText("x");
                    if (cur_mode == 0) cur_mode++;
                }
                break;
            case R.id.btn_divide:
                tap_eqal_again = false;
                if(cal_sign != 0) {
                    calculate(cal_sign, numbers[1]);
                    cal_sign = 4;
                } else {
                    cal_sign = 4;
                    textview_screen.setText("/");
                    if (cur_mode == 0) cur_mode++;
                }
                break;
            case R.id.btn_equal:
                if (!tap_eqal_again) {
                    tap_eqal_again = true;
                    temp2 = numbers[1];
                    temp_cal_sign = cal_sign;
                    calculate(cal_sign, numbers[1]);
                } else {
                    calculate(temp_cal_sign, temp2);
                }
                reset = true;
                cal_sign = 0;
                break;
            case R.id.btn_sign:
                numbers[cur_mode] = - numbers[cur_mode];
                textview_screen.setText((Integer.toString(numbers[cur_mode])));
                break;
/*
            case R.id.btn_dot:
                break;
*/
        }
    }


}
