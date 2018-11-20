package com.example.rudgn.hw5;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by rudgn on 2018-11-15.
 */

public class InformationOfDust extends AppCompatActivity {
    final static String openAirPollutionURL ="http://openapi.seoul.go.kr:" +
            "8088/634d5475466b3268363558586d7957/xml/DailyAverageAirQuality/1/1/";
    Document doc = null;
    TextView PM10;
    TextView PM25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationofdust);

        PM10 = (TextView)findViewById(R.id.PM10TextView2);
        PM25 = (TextView)findViewById(R.id.PM25TextView2);

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

        GetXMLTask task = new GetXMLTask();
        String urlString = openAirPollutionURL + date + "/" + location;
        task.execute(urlString);
    }

    //private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String strPM10;
            String strPM25;

            NodeList nodeList = doc.getElementsByTagName("row");

            Node node = nodeList.item(0);
            Element fstElmnt = (Element) node;
            NodeList PM10List  = fstElmnt.getElementsByTagName("PM10");
            NodeList PM25List  = fstElmnt.getElementsByTagName("PM25");

            strPM10 = PM10List.item(0).getChildNodes().item(0).getNodeValue();
            strPM25 = PM25List.item(0).getChildNodes().item(0).getNodeValue();

            PM10.setText(strPM10);
            PM25.setText(strPM25);

            super.onPostExecute(doc);
        }
    }//end inner class - GetXMLTask
}