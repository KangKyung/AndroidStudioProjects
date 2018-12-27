package com.example.rudgn.mobilehw3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;

public class MusicService extends Service{

    private final String TAG = "MusicService";

    private ArrayList<MusicData> list;
    private MediaPlayer mediaPlayer;
    private RemoteViews contentView;

    private NotificationManager mNoti;
    private Notification noti;

    private IntentFilter filter;
    private int nowPosotion;

    public static String MUSIC_SERVICE_FILTER = "MUSIC_SERVICE_FILTER";

    String MUSIC_PREV = "MUSIC_PREV";
    String MUSIC_NOW = "MUSIC_NOW";
    String MUSIC_NEXT = "MUSIC_NEXT";
    String MUSIC_CLOSE = "MUSIC_CLOSE";


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MusicService onDestroy");
        unregisterReceiver(btnReceiver);
        mediaPlayer.release();
        mediaPlayer = null;
        stopForeground(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();
        list = new ArrayList<>();

        filter = new IntentFilter();
        filter.addAction(MUSIC_PREV);
        filter.addAction(MUSIC_NOW);
        filter.addAction(MUSIC_NEXT);
        filter.addAction(MUSIC_CLOSE);

        registerReceiver(btnReceiver, filter);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.requestAudioFocus(focusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Log.e(TAG, "mediaPlayer error i : " + i + " , i1 : " + i1);
                return false;
            }
        });


    }

    private void MusicOn(int position) {

        mediaPlayer.reset();
        Uri musicURI = Uri.withAppendedPath(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "" + list.get(position).getMusicId());
        mediaPlayer = MediaPlayer.create(this, musicURI);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.e(TAG, "onCompletion");

                if(list.size() == nowPosotion + 1){
                    nowPosotion = 0;
                } else{
                    nowPosotion += 1;
                }

                ChangeNotiInfomation();
                MusicOn(nowPosotion);
            }
        });
    }

    private void ChangeNotiInfomation() {

        contentView.setTextViewText(R.id.txt_title_pend, list.get(nowPosotion).getMusicTitle());
        contentView.setTextViewText(R.id.txt_singer_pend, list.get(nowPosotion).getSinger());
        contentView.setImageViewUri(R.id.img_pend, list.get(nowPosotion).getMusicImg());

        noti.bigContentView = contentView;
        startForeground(2127, noti);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        nowPosotion = intent.getExtras().getInt("position");
        Log.e(TAG, "message : " + nowPosotion);

        list = intent.getParcelableArrayListExtra("list");
        Log.e(TAG, "message : " + list.get(nowPosotion).getMusicTitle());

        Intent tent = new Intent(MUSIC_SERVICE_FILTER);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, tent, PendingIntent.FLAG_UPDATE_CURRENT);

        contentView = new RemoteViews(getPackageName(), R.layout.activity_main);

        Intent prevIntent = new Intent(MUSIC_PREV);
        Intent nowIntent = new Intent(MUSIC_NOW);
        Intent nextIntent = new Intent(MUSIC_NEXT);
        Intent closeIntent = new Intent(MUSIC_CLOSE);

        PendingIntent pdIntentPrev = PendingIntent.getBroadcast(this, 0, prevIntent, 0);
        PendingIntent pdIntentNow = PendingIntent.getBroadcast(this, 0, nowIntent, 0);
        PendingIntent pdIntentNext = PendingIntent.getBroadcast(this, 0, nextIntent, 0);
        PendingIntent pdIntentClose = PendingIntent.getBroadcast(this, 0, closeIntent, 0);

        contentView.setTextViewText(R.id.txt_title_pend, list.get(nowPosotion).getMusicTitle());
        contentView.setTextViewText(R.id.txt_singer_pend, list.get(nowPosotion).getSinger());
        contentView.setImageViewUri(R.id.img_pend, list.get(nowPosotion).getMusicImg());

        contentView.setOnClickPendingIntent(R.id.music_prev, pdIntentPrev);
        contentView.setOnClickPendingIntent(R.id.music_now, pdIntentNow);
        contentView.setOnClickPendingIntent(R.id.music_next, pdIntentNext);
        contentView.setOnClickPendingIntent(R.id.music_close, pdIntentClose);

        Notification.Builder mBuilder = new Notification.Builder(this);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContent(contentView);
        noti = mBuilder.build();
        noti.flags = Notification.FLAG_NO_CLEAR;

        final Notification.BigPictureStyle big = new Notification.BigPictureStyle();



        MusicOn(nowPosotion);
//        mNoti.notify(2127, noti);
        startForeground(2127, noti);

        return START_NOT_STICKY;
    }

    BroadcastReceiver btnReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (action.equals(MUSIC_PREV)) {
                Log.e(TAG, "MUSIC_PREV");

                if (nowPosotion > 0) {

                    nowPosotion -= 1;
                    ChangeNotiInfomation();
                    MusicOn(nowPosotion);
                } else {    //다시 시작으로
                    ChangeNotiInfomation();
                    MusicOn(nowPosotion);
                }
            }

            if (action.equals(MUSIC_NOW)) {
                Log.e(TAG, "MUSIC_NOW");
            }

            if (action.equals(MUSIC_NEXT)) {
                Log.e(TAG, "MUSIC_NEXT");
                nowPosotion += 1;
                ChangeNotiInfomation();
                MusicOn(nowPosotion);
            }

            if (action.equals(MUSIC_CLOSE)) {
                Log.e(TAG, "MUSIC_CLOSE");
//                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.cancel(2127);
                stopForeground(true);
                mediaPlayer.reset();

                Intent closeIntent = new Intent(MUSIC_SERVICE_FILTER);
                sendBroadcast(closeIntent);
            }
        }
    };

    private AudioManager.OnAudioFocusChangeListener focusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    switch (focusChange) {

                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK):
                            Log.e(TAG, "OnAudioFocusChangeListener AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                            break;
                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT):
                            Log.e(TAG, "OnAudioFocusChangeListener AUDIOFOCUS_LOSS_TRANSIENT");
                            break;

                        case (AudioManager.AUDIOFOCUS_LOSS):
                            Log.e(TAG, "OnAudioFocusChangeListener AUDIOFOCUS_LOSS");

                            mediaPlayer.stop();
                            contentView.setImageViewResource(R.id.music_now, R.drawable.play_pause);

                            noti.bigContentView = contentView;
                            startForeground(2127, noti);

                            break;

                        case (AudioManager.AUDIOFOCUS_GAIN):
                            Log.e(TAG, "OnAudioFocusChangeListener AUDIOFOCUS_GAIN");
                            break;
                        default:
                            break;
                    }
                }
            };

//    @Override
//    public void onCompletion(MediaPlayer mediaPlayer) {
//
//        Log.e(TAG, "onCompletion");
//
//        if(list.size() == nowPosotion + 1){
//            nowPosotion = 0;
//        } else{
//            nowPosotion += 1;
//        }
//
//        ChangeNotiInfomation();
//        MusicOn(nowPosotion);
//    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e(TAG, "onTaskRemoved");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged");
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}