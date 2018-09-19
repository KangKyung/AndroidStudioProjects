package com.example.rudgn.application;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    public void onMyButton(View v) {
        switch(v.getId())
        case R.id.button_view1: {
            Toast.makeText(this, "OnClick Event!!", Toast.LENGTH_LONG).show();
            break;
        }
    }
}