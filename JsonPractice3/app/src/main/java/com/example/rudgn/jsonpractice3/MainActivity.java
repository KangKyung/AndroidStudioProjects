package com.example.rudgn.jsonpractice3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    void movieListjsonParser(String jsonString) {
        StringBuffer sb = new StringBuffer();

        String movieCd = null;
        String movieNm = null;

        String arraysum;
        try {
            JSONObject jObject = new JSONObject(jsonString);
            JSONArray jArray = jObject.getJSONObject("boxOfficeResult").getJSONArray("dailyBoxOfficeList");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject2 = jArray.getJSONObject(i);  // JSONObject 추출

                movieCd = jObject2.getString("movieCd");
                movieNm = jObject2.optString("movieNm");

                arraysum = movieCd + movieNm + "\n";

                textView.setText(sb.append(arraysum));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.parsetext);
        String resultText = "값이 없음";

        try {
            resultText = new Task().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        movieListjsonParser(resultText);

    }
}
