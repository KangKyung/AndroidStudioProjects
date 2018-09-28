package com.example.rudgn.a180921;

import android.content.ComponentName;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
public class MainActivity extends AppCompatActivity {
    private long mExitModeTime = 0L;

    public void onBackPressed() {
        if( mExitModeTime != 0 && SystemClock.uptimeMillis() - mExitModeTime < 3000) {
            finish();
        } else {
            Toast.makeText(this, "이전 키를 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        mExitModeTime = SystemClock.uptimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
*/

/*
public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_B) {
            Toast.makeText(this, "B가 눌러짐.", Toast.LENGTH_SHORT).show();
            return  true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button view1 = (Button)findViewById(R.id.view1);

        view1.setOnKeyListener(this);
    }
}
*/

public class MainActivity extends AppCompatActivity {
    private long mExitModeTime = 0L;

    public void onBackPressed() {
        if( mExitModeTime != 0 && SystemClock.uptimeMillis() - mExitModeTime < 3000) {
            finish();
        } else {
            Toast.makeText(this, "이전 키를 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        mExitModeTime = SystemClock.uptimeMillis();
    }

    public void onClick(View v) {
        Intent intent = new Intent();

        ComponentName componentName = new ComponentName(
                "com.example.rudgn.a180921",
                "com.example.rudgn.a180921.AActivity");

        intent.setComponent( componentName);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}