package com.example.rudgn.aactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void onClick(View v) {
        //  인텐트 하나를 생성한다
        Intent intent = new Intent();

        //  인텐트에 실행할 패키지의 액티비티 정보(패키지 이름, 액티비티 이름)를 설정한다
        ComponentName componentName = new ComponentName(
                "com.example.rudgn.bactivity",
                "com.example.rudgn.bactivity.BActivity");

        intent.setComponent(componentName);

        //  Parcelable 객체를 생성 및 데이터를 변경하고, 실행될 액티비티에게 전달할 인텐트에 추가한다.
        //====================================================================================
        SampleData sampleData = new SampleData();
        sampleData.setIntData(123456789);
        sampleData.setStrData("Parcelcable Object");

        intent.putExtra("SAMPLE_DATA", sampleData);
        //====================================================================================

        //  B액티비티를 실행한다
        startActivity(intent);
    }
}