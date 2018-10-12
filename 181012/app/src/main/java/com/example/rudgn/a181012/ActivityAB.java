package com.example.rudgn.a181012;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rudgn on 2018-10-12.
 */

public class ActivityAB extends Activity {
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);
    }

    public void onClick(View v) {
        //  1. B 액티비티가 실행되도록 전달받은 인텐트를 수정한다.
        Intent intent = getIntent();
        intent.setClass(this, ActivityB.class);
        // B 액티비티가 대신 처리하도록 인텐트 플래그를 추가한다.
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);

        startActivity(intent);
        finish();   //  요거 추가해주면 바로 activity_a로 이동함 !! ㅎㅎ
    }
}
