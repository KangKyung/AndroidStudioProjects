package com.example.rudgn.servicebind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MusicPlayerService extends Service {
    private static final String TAG = MusicPlayerService.class.getSimpleName();

    private final IBinder mBinder = new MusicPlayerBinder();
    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mBinder;
    }

    public boolean isPlaying() {
        return (mPlayer != null && mPlayer.isPlaying());
    }

    public void play() {
        Log.d(TAG, "play");

        mPlayer = MediaPlayer.create(this, R.raw.sample);
        mPlayer.setLooping(true);
        mPlayer.setVolume(100, 100);
        mPlayer.start();
    }

    public void stop() {
        Log.d(TAG, "stop");
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    public class MusicPlayerBinder extends Binder {
        MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }
}