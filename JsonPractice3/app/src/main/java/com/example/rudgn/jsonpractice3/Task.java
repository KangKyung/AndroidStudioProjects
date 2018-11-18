package com.example.rudgn.jsonpractice3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by rudgn on 2018-11-18.
 */

public class Task extends AsyncTask<String, Void, String> {

    String clientKey = "#########################";;
    private String str, receiveMsg;
    private final String ID = "########";

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

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


    public String[] foodlistjsonParser(String jsonString) {

        String seq_code = null;
        String food_name = null;
        String code = null;
        String kcal = null;
        String sell_com = null;
        String thumb_img = null;
        String food_type = null;
        String barcode = null;
        String volume = null;

        String[] arraysum = new String[8];
        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("food_list");
            for (int i = 0; i < jarray.length(); i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                code = jObject.optString("code");
                seq_code = jObject.optString("seq_code");
                food_name = jObject.optString("food_name");
                thumb_img = jObject.optString("thumb_img");
                sell_com = jObject.optString("sell_com");
                barcode = jObject.optString("barcode");
                volume = jObject.optString("volume");
                food_type = jObject.optString("food_type");

                arraysum[0] = code;
                arraysum[1] = seq_code;
                arraysum[2] = food_name;
                arraysum[3] = thumb_img;
                arraysum[4] = sell_com;
                arraysum[5] = barcode;
                arraysum[6] = volume;
                arraysum[7] = food_type;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;
    }
}
