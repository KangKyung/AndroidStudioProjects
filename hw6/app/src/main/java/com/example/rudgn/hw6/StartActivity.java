package com.example.rudgn.hw6;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rudgn on 2018-12-05.
 */

public class StartActivity extends Activity {
    TextView resultText;
    private IMyCounterServiceInterface binder;
    public boolean running = true;

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IMyCounterServiceInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private class GetCountThread implements Runnable {
        private Handler handler = new Handler();

        @Override
        public void run() {
                while(running) {
                    if (binder == null) {
                        continue;
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                resultText.setText(binder.getCount() + "");
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    try {
                            Thread.sleep(500);
                        }catch (InterruptedException e) {
                        e.printStackTrace();
                        }
                    }
                }

    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        resultText = (TextView)findViewById(R.id.resultText);
    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.startButton: {
                Intent intent = new Intent(StartActivity.this, InputActivity.class);
                Intent serviceIntent = new Intent(StartActivity.this, MyCounterService.class);
                //startService(serviceIntent);
                bindService(serviceIntent, connection, BIND_AUTO_CREATE);
                running = true;
                new Thread(new GetCountThread()).start();
                startActivityForResult(intent,0);
                break;
            }
        }
    }
}