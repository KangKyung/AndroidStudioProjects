package com.example.pradcticetimer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView Date;
    ProgressHandler handler;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date = (TextView) findViewById(R.id.Date);
        handler = new ProgressHandler();

        runTime();

    }

    public void runTime(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        time= sdf.format(new Date(System.currentTimeMillis()));

                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    }catch (InterruptedException ex) {}
                }
            }
        });
        thread.start();
    }

    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Date.setText(time);
        }
    }
}