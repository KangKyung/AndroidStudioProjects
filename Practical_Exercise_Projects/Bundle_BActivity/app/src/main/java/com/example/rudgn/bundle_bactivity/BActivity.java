package com.example.rudgn.bundle_bactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Intent intent = getIntent();
        Bundle bundleData = intent.getBundleExtra("SAMPLE_DATA");

        if(bundleData == null) {
            Toast.makeText(this, "Bundle Data Null !!!", Toast.LENGTH_LONG).show();

            return;
        }

        TextView receivedStr = (TextView)findViewById(R.id.intent_received_data);
        receivedStr.setText("Bundle String : " + bundleData.getString("STR_DATA") + "\n" + "Bundle Integer : " + bundleData.getInt("INT_DATA"));
    }
}
