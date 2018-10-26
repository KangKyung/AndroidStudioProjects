package com.example.rudgn.a180928;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        //  intent.addCategory(Intent.CATEGORY_BROWSABLE);  //  ACTION_MAIN으로 등록된 앱 중 BROWSABLE앱만 나타남
        //  intent.addCategory(Intent.CATEGORY_APP_MAPS);   //  맵기능의 앱만 나타남
        intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
        startActivity(intent);
    }
}