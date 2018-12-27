package com.example.rudgn.finalexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        switch ((v.getId())) {
            case R.id.num2: {
                Intent intent2 = new Intent(this, Q2.class);
                startActivity(intent2);
                break;
            }

            case R.id.num3: {
                Intent intent3 = new Intent(this, Q3.class);
                startActivity(intent3);
                break;
            }

            case R.id.num4: {
                Intent intent4 = new Intent(this, Q4.class);
                startActivity(intent4);
                break;
            }
        }
    }
}
