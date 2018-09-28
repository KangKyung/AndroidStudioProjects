package com.example.rudgn.aactivity;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void onClick(View v) {
        Intent intent = new Intent();

        intent.putExtra("NAME", "Superdroid");

        ComponentName componentName = new ComponentName(
                "com.example.rudgn.a180928",
                "com.example.rudgn.a180928.MainActivity");

        intent.setComponent( componentName);

        startActivity(intent);
    }
}
 /*
 SampleData sampleData = new SampleData();
 sampleData.setIntData(123456789);
 sampleData.setStringData("Serializable Object");

 intent.putExtra("SAMPLE_DATA", sampleData);

 startActivity(intent);
  */