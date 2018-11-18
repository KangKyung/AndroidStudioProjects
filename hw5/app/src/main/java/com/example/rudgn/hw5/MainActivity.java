package com.example.rudgn.hw5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.searchButton: {
                EditText tvDate = (EditText) findViewById(R.id.dateTextView);
                String date = tvDate.getText().toString();
                EditText tvLocation = (EditText) findViewById(R.id.localTextView);
                String location = tvLocation.getText().toString();

                Intent intent = new Intent(MainActivity.this, InformationOfDate.class);
                intent.putExtra("date", date);
                intent.putExtra("location", location);
                startActivity(intent);
                break;
            }
            case R.id.dustSearchButton: {
                EditText tvLocation = (EditText) findViewById(R.id.localTextView);
                String location = tvLocation.getText().toString();

                Intent intent = new Intent(MainActivity.this, InformationOfDust.class);
                intent.putExtra("location2", location);
                startActivity(intent);
                break;
            }
        }
    }
}
