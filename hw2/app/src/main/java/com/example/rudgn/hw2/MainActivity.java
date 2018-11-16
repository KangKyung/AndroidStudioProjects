package com.example.rudgn.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getAirPollution(View view) {
        EditText tvDate = (EditText) findViewById(R.id.dateTextView);
        String date = tvDate.getText().toString();

        EditText tvLocation = (EditText) findViewById(R.id.localTextView);
        String location = tvLocation.getText().toString();

        // 대기오염도를 읽어오는 API 호출
        OpenAirPollutionAPITask APAT = new OpenAirPollutionAPITask();

        try {
            AirPollution AP = APAT.execute(date,location).get();

            System.out.println("NO2 :"+AP.getNO2());
            System.out.println("O3 :"+AP.getO3());
            System.out.println("CO :"+AP.getCO());
            System.out.println("SO2 :"+AP.getSO2());
            System.out.println("PM10 :"+AP.getPM10());
            System.out.println("PM25 :"+AP.getPM25());

            TextView NO2 = (TextView)findViewById(R.id.NO2TextView);
            TextView O3 = (TextView)findViewById(R.id.O3TextView);
            TextView CO = (TextView)findViewById(R.id.COTextView);
            TextView SO2 = (TextView)findViewById(R.id.SO2TextView);
            TextView PM10 = (TextView)findViewById(R.id.PM10TextView);
            TextView PM25 = (TextView)findViewById(R.id.PM25TextView);

            String strNO2 = String.valueOf(AP.getNO2());
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

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.searchButton: {
                Intent intent = new Intent(this, InformationOfDate.class);
                startActivity(intent);
                getAirPollution(v); //  dkdhaskldjsadkl;askd;laskd;asj;dsaj;djasja;sjfo;sakdo;sajf;oasjf;oasjd;laskd;laskf;sakd;osaj;fljas;ldsal;dkasl;jf;laskdl;ask
                break;
            }
            case R.id.dustSearchButton: {
                Intent intent = new Intent(this, InformationOfDust.class);
                startActivity(intent);
                break;
            }
            case R.id.hW2Button: {
                Intent intent = new Intent(this, MyWebBrowser.class);
                startActivity(intent);
                break;
            }
        }
    }
}
