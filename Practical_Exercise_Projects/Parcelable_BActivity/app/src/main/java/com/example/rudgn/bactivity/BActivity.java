package com.example.rudgn.bactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        //  자신을 호출한 액티비티가 보낸 인텐트에 직렬화 객체를 추출한다.
        //==========================================================================================
        Intent intent = getIntent();
        SampleData sampleData = (SampleData)intent.getParcelableExtra("SAMPLE_DATA");

        if(sampleData == null) {
            Toast.makeText(this, "SampleData Null!!", Toast.LENGTH_LONG).show();
            return;
        }
        //==========================================================================================
        //  전달 받은 직렬화 객체 내용으 출력한다.
        //============================================================================================================================================
        TextView receivedStr = (TextView)findViewById(R.id.intent_received_data);   //  이건 다른예제 String name = intent.getStringExtra("NAME");

        //============================================================================================================================================
    }
}