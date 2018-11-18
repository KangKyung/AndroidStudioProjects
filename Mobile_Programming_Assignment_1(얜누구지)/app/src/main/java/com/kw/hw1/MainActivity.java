package com.kw.hw1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        Intent intent;

        intent = new Intent(this, CalculateActivity.class);
        tabSpec = tabHost.newTabSpec("FirstTab").setIndicator("Calculate").setContent(intent);
        tabHost.addTab(tabSpec);

        intent = new Intent(this, LinearActivity.class);
        tabSpec = tabHost.newTabSpec("SecondTab").setIndicator("LinearLayout").setContent(intent);
        tabHost.addTab(tabSpec);

        intent = new Intent(this, TableActivity.class);
        tabSpec = tabHost.newTabSpec("ThirdTab").setIndicator("TableLayout").setContent(intent);
        tabHost.addTab(tabSpec);

        tabHost.getTabWidget().setCurrentTab(0);
    }
}
