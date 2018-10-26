package com.example.rudgn.midtestexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityQuestion1 extends Activity {

    public void onMyButtonClick(View v) {
        switch (v.getId()) {
            case R.id.Question1_button1 : {
                Intent intent = new Intent(this, ActivityQuestion2_1.class);
                startActivity(intent);
                break;
            }
            case R.id.Question1_button2 : {
                Intent intent = new Intent(this, ActivityQuestion3.class);
                startActivity(intent);
                break;
            }
            case R.id.Question1_button3 : {
                Intent intent = new Intent(this, ActivityQuestion4_1.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
    }
}
