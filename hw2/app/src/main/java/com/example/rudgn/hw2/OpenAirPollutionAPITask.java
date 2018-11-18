package com.example.rudgn.hw2;

import android.os.AsyncTask;

/**
 * Created by rudgn on 2018-11-16.
 */

public class OpenAirPollutionAPITask extends AsyncTask<String, Void, AirPollution> {

    @Override
    public AirPollution doInBackground(String... params) {
        OpenAirPollutionAPIClient client = new OpenAirPollutionAPIClient();

        String date = params[0];
        String location = params[1];
        //  API 호출
        AirPollution AP = client.getAirPollution(date, location);
        //System.out.println("AirPollution : "+AP.getNO2());

        // 작업 후 리턴
        return AP;
    }
}
