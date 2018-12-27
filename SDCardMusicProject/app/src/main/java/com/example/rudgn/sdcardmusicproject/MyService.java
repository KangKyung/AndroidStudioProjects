package com.example.rudgn.sdcardmusicproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.io.IOException;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    MediaPlayer mPlayer = null;
    TextView mTextMessage;
    RadioGroup mRadioGroupAudio;
    ImageView btn;
    private RemoteViews contentView;

    @Override
    public void onCreate() {
        super.onCreate();

        Intent mMainIntent = new Intent(this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(
                this, 1, mMainIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        contentView = new RemoteViews(getPackageName(), R.layout.notification);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("음악 제목 출력")
                .setContentIntent(mPendingIntent)
                .setContentText("음악 출력 과정");
        mBuilder.setContent(contentView);
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, mBuilder.build());

    }

    public void onRadioInApp() {
        deletePlayer();
        mPlayer = MediaPlayer.create(this, R.raw.sample);
    }

    public void onRadioSdCard() {
        deletePlayer();
        String sdRootPath = /////////////////////////////////////////////////////////////////////해결하자 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
                Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdRootPath + "/storage/sdcard/" + "Sample.mp3";
        LoadMedia(filePath);
    }

    public void onRadioClickId(int id) {
        switch( id ) {
            case R.id.radioInApp :
                onRadioInApp();
                break;
            case R.id.radioSdCard :
                onRadioSdCard();
                break;
        }
    }

    //  나중에 삭제 ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ
    public void onRadioClick(View v) {
        onRadioClickId( v.getId() );
    }

    public void loadAduioSource() {
        int id = mRadioGroupAudio.getCheckedRadioButtonId();
        onRadioClickId( id );
    }

    public void deletePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.buttonStop :
                btn.setImageResource(R.drawable.play);
                i = 1;
                deletePlayer();
                loadAduioSource();
                break;
        }
    }

    int i = 1;
    public void onChangeClick(View v){
        if(i%2==1)
        {btn.setImageResource(R.drawable.pause);
            i = 2;
            mPlayer.start();

        }
        else {
            btn.setImageResource(R.drawable.play);
            i = 1;
            mPlayer.pause();}
    }


    public boolean LoadMedia(String filePath) {
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource(filePath);
        } catch (IOException e) {
            return false;
        }

        try {
            mPlayer.prepare();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
