package com.example.rudgn.a181123;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    private ServiceConnection mConnection =
            new ServiceConnection() {
                public void onServiceConnected(
                        ComponentName name, IBinder service )
                {
                    Log.d( "superdroid", "onServiceConnected()" );
                }

                public void onServiceDisconnected(
                        ComponentName name )
                {
                    Log.d( "superdroid", "onServiceDisconnected()" );
                }
            };

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 카운트 서비스 연결
        Intent serviceIntent = new Intent(
                this, CountService.class);

        bindService(serviceIntent,
                mConnection, BIND_AUTO_CREATE);


    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_count_btn: {
                Intent serviceIntent = new
                        Intent(this, CountService.class);

                startService(serviceIntent);
                break;
            }

            // 2. 카운트 서비스 종료
            case R.id.stop_count_btn: {
                Intent serviceIntent = new
                        Intent(this, CountService.class);
                stopService(serviceIntent);
                break;
            }

            case R.id.get_cur_count_number_btn: {
                break;
            }
        }
    }
}
