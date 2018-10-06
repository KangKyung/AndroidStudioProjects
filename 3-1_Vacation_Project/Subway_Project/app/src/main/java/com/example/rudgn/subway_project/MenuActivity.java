package com.example.rudgn.subway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button stationline = (Button) findViewById(R.id.button_stationline);
        Button toilet = (Button) findViewById(R.id.button_toilet);
        Button food = (Button) findViewById(R.id.button_food);

        stationline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(getApplicationContext(), SubwaylineActivity.class);
                startActivityForResult(intent, 102);
            }
        });
        toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent(getApplicationContext(), Search_ToiletActivity.class);
                startActivityForResult(intent, 103);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                Intent intent = new Intent(getApplicationContext(), Search_FoodActivity.class);
                startActivityForResult(intent, 104);
            }
        });
    }
}
