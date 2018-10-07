package com.kw.hw1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends Activity {

    double number1 = 0; // 피연산자
    double number2 = 0;
    double result;
    String stringResult;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);

        final EditText editText1 = (EditText)findViewById(R.id.editText1);
        final EditText editText2 = (EditText)findViewById(R.id.editText2);
        final TextView textresult = (TextView)findViewById(R.id.textresult);

        button1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                try {
                    number1 = Double.valueOf(editText1.getText().toString());
                    number2 = Double.valueOf(editText2.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "값을 입력해야 합니다.", 0).show();
                    return true;
                }

                result = number1 + number2;
                stringResult = String.format("%.1f", result);
                textresult.setText("계산 결과 :" + String.valueOf(stringResult));
                return false;
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                try {
                    number1 = Double.valueOf(editText1.getText().toString());
                    number2 = Double.valueOf(editText2.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "값을 입력해야 합니다.", 0).show();
                    return true;
                }

                result = number1 - number2;
                stringResult = String.format("%.1f", result);
                textresult.setText("계산 결과 :" + String.valueOf(stringResult));
                return false;
            }
        });

        button3.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                try {
                    number1 = Double.valueOf(editText1.getText().toString());
                    number2 = Double.valueOf(editText2.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "값을 입력해야 합니다.", 0).show();
                    return true;
                }

                result = number1 * number2;
                stringResult = String.format("%.1f", result);
                textresult.setText("계산 결과 :" + String.valueOf(stringResult));
                return false;
            }
        });

        button4.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                try {
                    number1 = Double.valueOf(editText1.getText().toString());
                    number2 = Double.valueOf(editText2.getText().toString());

                    if (number2 == 0) {
                        Toast.makeText(getApplicationContext(), "0으로는 나눌 수 없습니다.", 0).show();
                        return true;
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "값을 입력해야 합니다.", 0).show();
                    return true;
                }

                result = number1 / number2;
                stringResult = String.format("%.1f", result);
                textresult.setText("계산 결과 :" + String.valueOf(stringResult));
                return false;
            }
        });

        button5.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("WrongConstant")
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    number1 = Double.valueOf(editText1.getText().toString());
                    number2 = Double.valueOf(editText2.getText().toString());

                    if (number2 == 0) {
                        Toast.makeText(getApplicationContext(), "0으로는 나눌 수 없습니다.", 0).show();
                        return true;
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "값을 입력해야 합니다.", 0).show();
                    return true;
                }

                result = number1 % number2;
                stringResult = String.format("%.1f", result);
                textresult.setText("계산 결과 :" + String.valueOf(stringResult));
                return false;
            }
        });

    }
}
