package com.example.rudgn.hw2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by rudgn on 2018-11-15.
 */

public class InformationOfDate extends AppCompatActivity {
    final static String openAirPollutionURL ="http://openapi.seoul.go.kr:8088/634d5475466b3268363558586d7957/json/DailyAverageAirQuality/1/1/";
    TextView NO2;
    TextView O3;
    TextView CO;
    TextView SO2;
    TextView PM10;
    TextView PM25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationofdate);

        NO2 = (TextView)findViewById(R.id.NO2TextView);
        O3 = (TextView)findViewById(R.id.O3TextView);
        CO = (TextView)findViewById(R.id.COTextView);
        SO2 = (TextView)findViewById(R.id.SO2TextView);
        PM10 = (TextView)findViewById(R.id.PM10TextView);
        PM25 = (TextView)findViewById(R.id.PM25TextView);

        String resultText = "값이 없음";
        try {
            resultText = new Task().execute().get();
            movieListjsonParser(resultText);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class Task extends AsyncTask<String, Void, String> {
        private String str, receiveMsg;

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            Intent intent = getIntent();
            String date = intent.getExtras().getString("date");
            String location = intent.getExtras().getString("location");
            String urlString = openAirPollutionURL + date + "/" + location;

            try {
                url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return receiveMsg;
        }
    }

    void movieListjsonParser(String jsonString) {
        StringBuffer sb = new StringBuffer();

        String strNO2;
        String strO3 ;
        String strCO;
        String strSO2;
        String strPM10;
        String strPM25;

        try {
            JSONObject jObject = new JSONObject(jsonString);
            JSONArray jArray = jObject.getJSONObject("DailyAverageAirQuality").getJSONArray("row");
            JSONObject jObject2 = jArray.getJSONObject(0);  // JSONObject 추출

            strNO2 = jObject2.getString("NO2");
            strO3 = jObject2.optString("O3");
            strCO = jObject2.optString("CO");
            strSO2 = jObject2.optString("SO2");
            strPM10 = jObject2.optString("PM10");
            strPM25 = jObject2.optString("PM25");

            NO2.setText(strNO2);
            O3.setText(strO3);
            CO.setText(strCO);
            SO2.setText(strSO2);
            PM10.setText(strPM10);
            PM25.setText(strPM25);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
