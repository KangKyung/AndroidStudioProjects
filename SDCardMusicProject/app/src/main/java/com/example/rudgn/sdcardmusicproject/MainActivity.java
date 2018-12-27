package com.example.rudgn.sdcardmusicproject;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {
    MediaPlayer mPlayer = null;
    TextView mTextMessage;
    RadioGroup mRadioGroupAudio;
    ImageView btn;
    private RemoteViews contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else
            setting();

        mRadioGroupAudio = (RadioGroup)findViewById(R.id.radioGroupAudio);
        mRadioGroupAudio.check(R.id.radioInApp);
        onRadioInApp();

        btn=(ImageView)findViewById(R.id.buttonPlay);

        Intent mMainIntent = new Intent(this, MainActivity.class);

        PendingIntent mPendingIntent = PendingIntent.getActivity(
                this, 1, mMainIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );



    }

    private void setting() {}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setting();
            else
                Toast.makeText(this, "권한 거부", Toast.LENGTH_SHORT).show();
        }
    }


    public void onRadioInApp() {
        deletePlayer();
        mPlayer = MediaPlayer.create(this, R.raw.sample);
    }

    public void onRadioSdCard() {
        deletePlayer();
        String sdRootPath = /////////////////////////////////////////////////////////////////////해결하자 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
                Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = sdRootPath + "Sample.mp3";
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