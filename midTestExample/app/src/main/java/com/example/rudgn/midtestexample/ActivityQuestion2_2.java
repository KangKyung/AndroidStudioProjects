package com.example.rudgn.midtestexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rudgn on 2018-10-26.
 */

public class ActivityQuestion2_2 extends Activity {

    public void onMyButtonClick(View v) {
        switch (v.getId()) {
            case R.id.Question2_button4 : {
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_2);
    }
}