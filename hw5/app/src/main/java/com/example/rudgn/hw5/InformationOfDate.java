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
    final static String openAirPollutionURL ="http://www.kma.go.kr/wid/queryDFS.jsp";
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
        String urlString = openAirPollutionURL + "?gridx=" + date + "&gridy=" + location;
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

            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("data");
            //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

            //날씨 데이터를 추출
            Node node = nodeList.item(0); //data엘리먼트 노드
            Element fstElmnt = (Element) node;
            NodeList nameList  = fstElmnt.getElementsByTagName("temp");
            Element nameElement = (Element) nameList.item(0);
            nameList = nameElement.getChildNodes();
            strPM10 = "미세먼지 테스트 텍스트입니다."+ ((Node) nameList.item(0)).getNodeValue() +" ,";

            NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
            //<wfKor>맑음</wfKor> =====> <wfKor> 태그의 첫번째 자식노드는 TextNode 이고 TextNode의 값은 맑음
            strPM25 = "초미세먼지 테스트 텍스트입니다.= "+  websiteList.item(0).getChildNodes().item(0).getNodeValue();

            /*
            NO2.setText(strNO2);
            O3.setText(strO3);
            CO.setText(strCO);
            SO2.setText(strSO2);*/
            PM10.setText(strPM10);
            PM25.setText(strPM25);

            super.onPostExecute(doc);
        }
    }//end inner class - GetXMLTask
}