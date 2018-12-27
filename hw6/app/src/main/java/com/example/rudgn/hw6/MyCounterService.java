package com.example.rudgn.hw6;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyCounterService extends Service {
    private int count;
    private boolean isStop;
    public MyCounterService() { }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread counter = new Thread(new Counter());
        counter.start();
    }

    IMyCounterServiceInterface.Stub binder = new IMyCounterServiceInterface.Stub() {
        @Override
        public int getCount() throws RemoteException {
            return count;
        }
    };

    @Override
    public IBinder onBind(Intent intent) { return binder; }



    private class Counter implements Runnable {
        private Handler handler = new Handler();

        @Override
        public void run() {
            while (true) {
                if (isStop) {
                    Intent intent = new Intent(MyCounterService.this, InputActivity.class);
                    intent.putExtra("count", count);
                    break;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Toast로 Count 띄우기
                        Toast.makeText(getApplicationContext() , count + "" , Toast.LENGTH_SHORT).show();
                    }
                });

                try {
                    Thread.sleep(1000);
                    count++;
                }
                catch (InterruptedException e) { e.printStackTrace(); }
            }

            handler.post(new Runnable() {
                @Override
                public void run() { Toast.makeText(getApplicationContext(), "서비스가 종료되었습니다.", Toast.LENGTH_SHORT).show(); }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }
}