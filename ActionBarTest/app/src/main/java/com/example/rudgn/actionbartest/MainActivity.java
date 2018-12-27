package com.example.rudgn.actionbartest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View v) {
        ImageButton btn=(ImageButton) v;
        Intent service = new Intent(MainActivity.this, Service.class);
        if (v.getId() == R.id.buttonPlay && !Service.IS_SERVICE_RUNNING) {
            service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            Service.IS_SERVICE_RUNNING = true;
            btn.setImageResource(R.drawable.pause);
            startService(service);
        } else if (v.getId() == R.id.buttonPlay && Service.IS_SERVICE_RUNNING) {
            service.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            Service.IS_SERVICE_RUNNING = false;
            btn.setImageResource(R.drawable.play);
            startService(service);
        } else if(v.getId() == R.id.buttonStop) {
            stopService(service);
        }
    }
}
