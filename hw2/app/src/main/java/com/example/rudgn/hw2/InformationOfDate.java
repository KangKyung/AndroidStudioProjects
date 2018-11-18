package com.example.rudgn.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;

/**
 * Created by rudgn on 2018-11-15.
 */

public class InformationOfDate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationofdate);

        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");
        String location = intent.getExtras().getString("location");

        // 대기오염도를 읽어오는 API 호출
        OpenAirPollutionAPITask APAT = new OpenAirPollutionAPITask();

        try {
            AirPollution AP = APAT.execute(date,location).get();

            Toast.makeText(this, "왜안돼?? ㅠㅠㅠ", Toast.LENGTH_LONG).show();

            TextView NO2 = (TextView)findViewById(R.id.NO2TextView);
            TextView O3 = (TextView)findViewById(R.id.O3TextView);
            TextView CO = (TextView)findViewById(R.id.COTextView);
            TextView SO2 = (TextView)findViewById(R.id.SO2TextView);
            TextView PM10 = (TextView)findViewById(R.id.PM10TextView);
            TextView PM25 = (TextView)findViewById(R.id.PM25TextView);

            String strNO2 = String.valueOf(AP.getNO2());    //
            String strO3 = String.valueOf(AP.getO3());
            String strCO = String.valueOf(AP.getCO());
            String strSO2 = String.valueOf(AP.getSO2());
            String strPM10 = String.valueOf(AP.getPM10());
            String strPM25 = String.valueOf(AP.getPM25());

            NO2.setText(strNO2);
            O3.setText(strO3);
            CO.setText(strCO);
            SO2.setText(strSO2);
            PM10.setText(strPM10);
            PM25.setText(strPM25);
            //  get 작업          @@@@@@@@@@@@@@@ 이부분 나중에 인텐트(겟인텐트같은거)로 따로 작업필요함 !!!!!    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}