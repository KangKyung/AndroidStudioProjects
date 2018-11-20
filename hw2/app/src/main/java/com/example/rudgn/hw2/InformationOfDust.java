package com.example.rudgn.hw2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

/**
 * Created by rudgn on 2018-11-15.
 */

public class InformationOfDust extends AppCompatActivity {
    final static String openAirPollutionURL ="http://openapi.seoul.go.kr:8088/634d5475466b3268363558586d7957/json/DailyAverageAirQuality/1/1/";
    TextView PM10;
    TextView PM25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationofdust);

        PM10 = (TextView)findViewById(R.id.PM10TextView2);
        PM25 = (TextView)findViewById(R.id.PM25TextView2);

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
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            String strMonth = String.valueOf(month);
            if (month >=1 && month <= 9)
                strMonth = "0" + strMonth;
            int day = cal.get(Calendar.DATE);
            String strDay = String.valueOf(day);
            if (day >=1 && day <= 9)
                strDay = "0" + strDay;
            String date = String.valueOf(year) + strMonth + strDay;
            String location = intent.getExtras().getString("location2");
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

        String strPM10;
        String strPM25;

        try {
            JSONObject jObject = new JSONObject(jsonString);
            JSONArray jArray = jObject.getJSONObject("DailyAverageAirQuality").getJSONArray("row");

            JSONObject jObject2 = jArray.getJSONObject(0);  // JSONObject 추출

            strPM10 = jObject2.optString("PM10");
            strPM25 = jObject2.optString("PM25");

            PM10.setText(strPM10);
            PM25.setText(strPM25);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
