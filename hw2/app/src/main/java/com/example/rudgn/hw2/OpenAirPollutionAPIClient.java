package com.example.rudgn.hw2;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rudgn on 2018-11-16.
 */

public class OpenAirPollutionAPIClient {
    final static String openAirPollutionURL = "http://openapi.seoul.go.kr:8088/634d5475466b3268363558586d7957/json/DailyAverageAirQuality/1/1/";

    public AirPollution getAirPollution(String date, String location){

        AirPollution AP;
        String urlString = openAirPollutionURL + "?date="+date+"&location="+location;

        try {
            // call API by using HTTPURLConnection
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            // parse JSON
            AP = parseJSON(json);
            AP.setDate(date);
            AP.setLocation(location);

        }catch(MalformedURLException e){
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;

        }catch(JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;

        }catch(IOException e){
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;

        }

        // set Weather Object
        return AP;
    }

    private AirPollution parseJSON(JSONObject json) throws JSONException {
        AirPollution AP = new AirPollution();
        AP.setNO2(json.getInt("NO2"));
        AP.setO3(json.getInt("O3"));
        AP.setCO(json.getInt("CO"));
        AP.setSO2(json.getInt("SO2"));
        AP.setPM10(json.getInt("PM10"));
        AP.setPM25(json.getInt("PM25"));

        //AP.set작업
        return AP;
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) { sb.append(line); }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
