package com.example.rudgn.bundle_aactivity;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void onClick(View v) {
        //  인텐트 하나를 생성한다
        Intent intent = new Intent();

        intent.putExtra("NAME", "Superdroid");
        //  인텐트에 실행할 패키지의 액티비티 정보(패키지 이름, 액티비티 이름)를 설정한다
        ComponentName componentName = new ComponentName(
                "com.example.rudgn.bundle_bactivity",
                "com.example.rudgn.bundle_bactivity.BActivity");

        intent.setComponent(componentName);

        Bundle bundleData = new Bundle();
        bundleData.putInt("INT_DATA", 123456789);
        bundleData.putString("STR_DATA", "Bundle String");

        intent.putExtra("SAMPLE_DATA", bundleData);

        startActivity(intent);
    }
}
