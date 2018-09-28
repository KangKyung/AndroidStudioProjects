package com.example.rudgn.a180921;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rudgn on 2018-09-21.
 */

public class AActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aactivity);
    }

    public void onClick(View v) {
        Intent intent = new Intent();

        intent.putExtra("NAME", "Superdroid");

        ComponentName componentName = new ComponentName(
                "com.example.rudgn.a180921",
                "com.example.rudgn.a180921.BActivity");

        intent.setComponent( componentName);

        startActivity(intent);
    }
}
