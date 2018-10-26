package com.example.rudgn.a181026;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    int mCount = 0;
    Thread countThread;
    TextView txt_Count;
    Handler mHandler = new Handler();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_Count = (TextView) findViewById(R.layout.activity_main);
        Log.d("superdroid", "onCreate()");
    }

    public void onClick(View v) {
        countThread = new Thread("Count Thread") {
            @Override
            public void run() {
                while (true) {
                    mCount++;
                    Runnable callback = new Runnable() {
                        @Override
                        public void run() {
                            Log.d("superdroid", "Current Count : " + mCount);
                            txt_Count.setText("Count : " + mCount);
                        }
                    };
                    Message message = Message.obtain(mHandler, callback);
                    mHandler.sendMessage(message);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
    }
}