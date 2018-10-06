package com.example.rudgn.a181005practice;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        startActivity(new Intent(this, BActivity.class));
    }

    @Override
    protected void onStart() {
    super.onStart();
        Log.d("superdroid", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("superdroid", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("superdroid", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("superdroid", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("superdroid", "onDestroy()");
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.w("superdroid", "onConfigurationChanged()");
    }
};