package com.example.rudgn.mymp3playerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by rudgn on 2018-12-16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(),//현재제어권자
                                            MyService.class); // 이동할 컴포넌트
        startService(intent); // 서비스 시작


    } // end of onCreate
} // end of class