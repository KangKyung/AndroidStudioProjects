package com.example.rudgn.review;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText mEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("superdroid", "onSaveInstanceState");
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.w("superdroid", "onSaveInstanceState()");

        String backupString = mEditText.getText().toString();
        outState.putString("BACKUP_STRING", backupString);

        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.w("superdroid", "onRestoreInstanceState()");

        if(savedInstanceState != null) {
            mEditText.setText(savedInstanceState.getString("BACKUP_STRING"));
        }
    }
}
