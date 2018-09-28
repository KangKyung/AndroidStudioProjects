package com.example.rudgn.mobile_programming_assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        Intent intent;

        intent = new Intent(this, CalculateActivity.class);
        tabSpec = tabHost.newTabSpec("FirstTab").setIndicator("Calculate").setContent(intent);
        tabHost.addTab(tabSpec);

        intent = new Intent(this, CalculateActivity.class);
        tabSpec = tabHost.newTabSpec("SecondTab").setIndicator("LinearLayout").setContent(intent);
        tabHost.addTab(tabSpec);

        intent = new Intent(this, CalculateActivity.class);
        tabSpec = tabHost.newTabSpec("ThirdTab").setIndicator("TableLayout").setContent(intent);
        tabHost.addTab(tabSpec);

        tabHost.getTabWidget().setCurrentTab(0);
    }
}
