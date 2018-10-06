package com.example.rudgn.assignment_weeks_5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rudgn on 2018-10-04.
 */

public class BillActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);

        TextView saAmount = (TextView)findViewById(R.id.saAmount);
        TextView waAmount = (TextView)findViewById(R.id.waAmount);
        TextView wiAmount = (TextView)findViewById(R.id.wiAmount);
        TextView saWeight = (TextView)findViewById(R.id.saWeight);
        TextView waWeight = (TextView)findViewById(R.id.waWeight);
        TextView wiWeight = (TextView)findViewById(R.id.wiWeight);
        TextView saPrice = (TextView)findViewById(R.id.saPrice);
        TextView waPrice = (TextView)findViewById(R.id.waPrice);
        TextView wiPrice = (TextView)findViewById(R.id.wiPrice);

        // int getInt = getIntent().getIntExtra("saAmount", amountresult1);
        saAmount.setText(String.valueOf(amountresult1)); // getInt 넣어줘야함
        waAmount.setText(String.valueOf(amountresult2));
        wiAmount.setText(String.valueOf(amountresult3));
        saWeight.setText(String.valueOf(saWeight1));
        waWeight.setText(String.valueOf(waWeight1));
        wiWeight.setText(String.valueOf(wiWeight1));
        saPrice.setText(String.valueOf(saPrice1));
        waPrice.setText(String.valueOf(waPrice1));
        wiPrice.setText(String.valueOf(wiPrice1));


        Button button = (Button) findViewById(R.id.backButton);    //  확인 버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
