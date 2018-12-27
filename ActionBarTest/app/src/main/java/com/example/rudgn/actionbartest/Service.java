package com.example.rudgn.actionbartest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class Service extends android.app.Service {
    private static final String LOG_TAG = "Service";
    public static boolean IS_SERVICE_RUNNING = false;
    private double startTime = 0;
    private int backwardTime = 1000;
    MediaPlayer mp; // 음악 재생을 위한 객체

    @Override
    public void onCreate() {
        super.onCreate();
        // 서비스에서 가장 먼저 호출됨(최초에 한번만)
        Log.d("test", "서비스의 onCreate");
        mp = MediaPlayer.create(this, R.raw.sample);
        mp.setLooping(false); // 반복재생
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Log.i(LOG_TAG, "Clicked Previous");
            //  10초전으로 이동
            int temp = (int)startTime;
            if((temp - backwardTime) > 0) {
                startTime = startTime - backwardTime;
                mp.seekTo((int)startTime);
                Toast.makeText(this, "Clicked Previous 10 Seconds!", Toast.LENGTH_SHORT).show();
            } else {
                startTime = 0;
                mp.seekTo((int)startTime);
                startTime = mp.getCurrentPosition();
                Toast.makeText(this, "Clicked Previous! But it is 0Seconds!", Toast.LENGTH_SHORT).show();
            }

        } else if (intent.getAction().equals(Constants.ACTION.PAUSE_ACTION)) {
            Log.i(LOG_TAG, "Clicked Pause");
            mp.pause(); //  음악 일시 정지

            Toast.makeText(this, "Clicked Pause!", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            Log.i(LOG_TAG, "Clicked Play");
            mp.start(); //  음악 재생
            startTime = mp.getCurrentPosition();

            Toast.makeText(this, "Clicked Play!", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            Log.i(LOG_TAG, "Clicked Next");
            mp.pause();
            startTime = 0;
            mp.seekTo((int)startTime);// 음악 중지

            Toast.makeText(this, "Clicked Stop!", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, Service.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, Service.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent pauseIntent = new Intent(this, Service.class);
        pauseIntent.setAction(Constants.ACTION.PAUSE_ACTION);
        PendingIntent ppauseIntent = PendingIntent.getService(this, 0,
                pauseIntent, 0);

        Intent nextIntent = new Intent(this, Service.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.albumart);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Sample.mp3")
                .setSmallIcon(R.drawable.play)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(R.drawable.back, "",
                        ppreviousIntent)
                .addAction(R.drawable.play, "",
                        pplayIntent)
                .addAction(R.drawable.pause, "",
                        ppauseIntent)
                .addAction(R.drawable.stop, "",
                        pnextIntent).build();
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        Log.i(LOG_TAG, "In onDestroy");
        Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}