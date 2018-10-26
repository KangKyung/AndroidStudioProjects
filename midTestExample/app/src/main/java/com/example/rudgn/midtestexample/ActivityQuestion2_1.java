package com.example.rudgn.midtestexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rudgn on 2018-10-26.
 */

public class ActivityQuestion2_1 extends Activity {

    public void onMyButtonClick(View v) {
        switch (v.getId()) {
            case R.id.Question2_button1 : {

                break;
            }
            case R.id.Question2_button2 : {

                break;
            }
            case R.id.Question2_button3 : {
                Intent intent = new Intent(this, ActivityQuestion2_2.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_1);
    }
}
