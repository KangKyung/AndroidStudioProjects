package com.example.rudgn.subway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Search_FoodActivity extends AppCompatActivity {
    FrameLayout s_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__food);

        s_food = (FrameLayout) findViewById(R.id.s_food);
        Button searchfood = (Button) findViewById(R.id.button_searchfood);

        searchfood.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.drawable.subway_line1, s_food, true);
            } // 수정 해야함 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        });
    }
}
