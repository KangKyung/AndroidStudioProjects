package com.example.rudgn.assignment_weeks_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int saPrice1 = 0, waPrice1 = 0, wiPrice1 = 0;
    double saWeight1, waWeight1, wiWeight1;
    int amountresult1 = 0, amountresult2 = 0, amountresult3 = 0;
    double totalWeight = 0;
    int totalPrice = 0, totalamount = 0;
    String stringtotalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BillActivity.class);
        intent.putExtra("amountA", 1);

        Button button1 = (Button) findViewById(R.id.button);    //  확인 버튼
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);

        final TextView amount1 = (TextView) findViewById(R.id.amount1);
        final TextView amount2 = (TextView) findViewById(R.id.amount2);
        final TextView amount3 = (TextView) findViewById(R.id.amount3);

        // final Intent intent2 = new Intent(this, BillActivity.class);

        // 수량 조절 버튼
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountresult1++;
                amount1.setText(String.valueOf(amountresult1));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountresult2--;
                amount2.setText(String.valueOf(amountresult2));
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountresult2++;
                amount2.setText(String.valueOf(amountresult2));
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountresult3--;
                amount3.setText(String.valueOf(amountresult3));
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountresult3++;
                amount3.setText(String.valueOf(amountresult3));
            }
        });

        // 확인 버튼
        button1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                try {
                    saPrice1 = 25000 * amountresult1;
                    waPrice1 = 500 * amountresult2;
                    wiPrice1 = 10000 * amountresult3;
                    totalPrice = saPrice1 + waPrice1 + wiPrice1;
                    saWeight1 = 1 * amountresult1;
                    waWeight1 = 0.3 * amountresult2;
                    wiWeight1 = 0.7 * amountresult3;
                    totalWeight = saWeight1 + waWeight1 + wiWeight1;
                    totalamount = amountresult1 + amountresult2 + amountresult3;

                    //  intent2.putExtra("amountA", amountresult1);

                    if(totalamount <= 0) {
                        Toast.makeText(getApplicationContext(), "주문하고자 하는 수량이 0개 입니다.", 0).show();
                        return true;
                    }
                    else if(totalWeight >= 5.0) {
                        Toast.makeText(getApplicationContext(), "주문 가능한 무게가 초과되었습니다. 현재 무게 : " + totalWeight + "kg", 0).show();
                        return true;
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "값을 입력해야 합니다.", 0).show();
                    return true;
                }

                stringtotalWeight = String.format("%.1f", totalWeight);
                Toast.makeText(getApplicationContext(), "총 무게 : " + stringtotalWeight + "kg", 0).show();

                Intent intent = new Intent(getApplicationContext(), BillActivity.class);
                startActivityForResult(intent, 101);
                return false;
            }
        });
    }
}
