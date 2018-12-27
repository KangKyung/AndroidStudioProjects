package com.example.rudgn.mymp3playerapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

// 서비스 클래스를 구현하려면, Service 를 상속받는다
public class MyService extends Service {
    MediaPlayer mp; // 음악 재생을 위한 객체

    private RemoteViews contentView;
    private boolean isPrepared;

    @Override
    public IBinder onBind(Intent intent) {
        // Service 객체와 (화면단 Activity 사이에서)
        // 통신(데이터를 주고받을) 때 사용하는 메서드
        // 데이터를 전달할 필요가 없으면 return null;
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // 서비스에서 가장 먼저 호출됨(최초에 한번만)
        Log.d("test", "서비스의 onCreate");
        mp = MediaPlayer.create(this, R.raw.sample);
        mp.setLooping(false); // 반복재생
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 서비스가 종료될 때 실행
        mp.stop(); // 음악 종료
        Log.d("test", "서비스의 onDestroy");
    }


    //  MediaPlayer를 재생가능한 상태로 만들어주는 prepare
    private void prepare() {
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //   음악을 재생할 수 있는 play
    public void play(int position) {
        mp.stop();
        prepare();
    }

    public void play() {
        if (isPrepared) {
            mp.start();
        }
    }

    public void pause() {
        if (isPrepared) {
            mp.pause();
        }
    }

    private void stop() {
        mp.stop();
        mp.reset();
    }
}