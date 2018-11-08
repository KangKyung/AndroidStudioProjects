package com.example.rudgn.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    BroadcastReceiver mFileDownloadedReceiver = null;

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(
                "com.superdroid.test.Broadcasting.action.FILE_DOWNLOADED");

        mFileDownloadedReceiver = new BroadcastReceiver()
        {
            public void onReceive(Context context, Intent intent )
            {
                String fileName = intent.getStringExtra( "FILE_NAME" );

                Toast.makeText( context, "동적 Receiver : FILE_DOWNLOADED \n "
                                + fileName,
                        Toast.LENGTH_LONG ).show();
            }
        };

        registerReceiver( mFileDownloadedReceiver, intentFilter );
    }

    public void onClick( View v )
    {
        Intent intent = new Intent();

        intent.setAction("com.superdroid.test.Broadcasting.action.FILE_DOWNLOADED" );

        intent.putExtra( "FILE_NAME", "superdroid.png" );

        sendBroadcast( intent );
    }

}
