package com.example.rudgn.subway_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Search_ToiletActivity extends AppCompatActivity {
    FrameLayout s_toilet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__toilet);

        s_toilet = (FrameLayout) findViewById(R.id.s_toilet);
        Button searchtoilet = (Button) findViewById(R.id.button_searchtoilet);

        searchtoilet.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.drawable.subway_line1, s_toilet, true);
            } // 수정 해야함 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        });
    }
}
