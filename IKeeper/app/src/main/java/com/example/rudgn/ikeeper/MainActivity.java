package com.example.rudgn.ikeeper;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reigtrationToC2dm();

    }

    private void reigtrationToC2dm() {
        Log.i("reigtrationToC2dm","start...");
        Intent registIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
        registIntent.putExtra("app", PendingIntent.getBroadcast(this, 0, new Intent(), 0));
        registIntent.putExtra("sender", "mhb8436@gmail.com");
        startService(registIntent);

        Log.i("reigtrationToC2dm","started...");

    }
}