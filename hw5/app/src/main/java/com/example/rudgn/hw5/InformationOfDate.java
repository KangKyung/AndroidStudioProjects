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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by rudgn on 2018-11-15.
 */

public class InformationOfDate extends AppCompatActivity {
    final static String openAirPollutionURL ="http://openapi.seoul.go.kr:" + "8088/634d5475466b3268363558586d7957/xml/DailyAverageAirQuality/1/1/";
    Document doc = null;
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

        Intent intent = getIntent();
        String date = intent.getExtras().getString("date");
        String location = intent.getExtras().getString("location");

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
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String strNO2;
            String strO3 ;
            String strCO;
            String strSO2;
            String strPM10;
            String strPM25;

            NodeList nodeList = doc.getElementsByTagName("row");

            Node node = nodeList.item(0);
            Element fstElmnt = (Element) node;
            NodeList NO2List  = fstElmnt.getElementsByTagName("NO2");
            NodeList O3List  = fstElmnt.getElementsByTagName("O3");
            NodeList COList  = fstElmnt.getElementsByTagName("CO");
            NodeList SO2List  = fstElmnt.getElementsByTagName("SO2");
            NodeList PM10List  = fstElmnt.getElementsByTagName("PM10");
            NodeList PM25List  = fstElmnt.getElementsByTagName("PM25");

            strNO2 = NO2List.item(0).getChildNodes().item(0).getNodeValue();
            strO3 = O3List.item(0).getChildNodes().item(0).getNodeValue();
            strCO = COList.item(0).getChildNodes().item(0).getNodeValue();
            strSO2 = SO2List.item(0).getChildNodes().item(0).getNodeValue();
            strPM10 = PM10List.item(0).getChildNodes().item(0).getNodeValue();
            strPM25 = PM25List.item(0).getChildNodes().item(0).getNodeValue();

            NO2.setText(strNO2);
            O3.setText(strO3);
            CO.setText(strCO);
            SO2.setText(strSO2);
            PM10.setText(strPM10);
            PM25.setText(strPM25);

            super.onPostExecute(doc);
        }
    }//end inner class - GetXMLTask
}