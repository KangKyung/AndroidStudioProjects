package com.example.rudgn.subway_app;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();//변수생성

        TabHost.TabSpec tabSpecRail = tabHost.newTabSpec("RAIL").setIndicator("노선도");
        tabSpecRail.setContent(R.id.tabRail);
        tabHost.addTab(tabSpecRail);

        TabHost.TabSpec tabSpecEat = tabHost.newTabSpec("EAT").setIndicator("맛 집");
        tabSpecEat.setContent(R.id.tabEat);
        tabHost.addTab(tabSpecEat);

        TabHost.TabSpec tabSpecHelp = tabHost.newTabSpec("HELP").setIndicator("편의시설");
        tabSpecHelp.setContent(R.id.tabHelp);
        tabHost.addTab(tabSpecHelp);

        tabHost.setCurrentTab(0);
    }
}
