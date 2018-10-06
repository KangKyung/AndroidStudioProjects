package com.example.rudgn.myframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageview;
    ImageView imageview2;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = (ImageView) findViewById(R.id.imageView5);
        imageview2 = (ImageView) findViewById(R.id.imageView6);
    }

    public void onButton1Clicked(View v) {
        index += 1;
        if(index > 1)
            index = 0;
        if(index == 0) {
            imageview.setVisibility(View.VISIBLE);
            imageview2.setVisibility(View.INVISIBLE);
        } else if(index == 1) {
            imageview.setVisibility(View.INVISIBLE);
            imageview2.setVisibility(View.VISIBLE);
        }
    }
}
