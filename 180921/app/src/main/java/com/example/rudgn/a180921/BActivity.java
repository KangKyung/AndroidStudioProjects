package com.example.rudgn.a180921;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rudgn on 2018-09-21.
 */

public class BActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bactivity);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        
        @SuppressLint("WrongViewCast") TextView receivedStr = (TextView)findViewById( R.id.intent_received_data );
        receivedStr.setText("" + name);
    }

}