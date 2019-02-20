package com.example.rudgn.ikeeper;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rudgn on 2019-01-14.
 */

public class Receiver extends BroadcastReceiver {

    static String TAG = "Receiver";
    static String registration_id;
    static String message;
    Context context;
    String device_id;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        if (intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
            TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            device_id = telManager.getDeviceId();
            try {
                handleRegistration(context, intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")){
            handleReceive(context, intent);
        }
    }

    private void handleReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("msg"), 1000).show();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher_foreground ,"c2dm 수신",System.currentTimeMillis());
        intent.setClassName("com.example.rudgn.ikeeper", "MainActivity");
        //PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        //notification.icon = R.drawable.ic_launcher_background;
        //notification.setLatestEventInfo(context, "C2DM ", intent.getStringExtra("data.msg"), pendingIntent);
        //notificationManager.notify(0, notification);

        PendingIntent pi1 = null;
        pi1 = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pi1)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("C2DM ")
                .setContentText(intent.getStringExtra("data.msg"))
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        notification = builder.build();
        notificationManager.notify(0, notification);

    }

    private void handleRegistration(Context context, Intent intent) throws Exception {
        registration_id = intent.getStringExtra("registration_id");

        if(intent.getStringExtra("error") != null){
            Log.i(TAG, "error:"+intent.getStringExtra("error"));
        }else if(intent.getStringExtra("unregistered") != null){
            Log.i(TAG, "unregistered:"+intent.getStringExtra("unregistered"));
        }
        Log.i(TAG, "registration_id:"+intent.getStringExtra("registration_id"));

        if(registration_id != null){
            Toast.makeText(context, intent.getStringExtra("registration_id"), 1000).show();
            String msg = null;

            Thread th = new Thread(){
                public void run(){
                    try {
                        store(device_id, registration_id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            th.start();
        }
    }

    private String store(String device_id, String registration_id) throws Exception {
        StringBuilder postBuilder = new StringBuilder();
        postBuilder.append("device_id").append("=").append(device_id);
        postBuilder.append("&").append("registration_id").append("=").append(registration_id);

        byte[] post = postBuilder.toString().getBytes("UTF-8");
        URL url = new URL("http://mhb8436.appspot.com/device");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(post.length));

        OutputStream out = conn.getOutputStream();
        out.write(post);
        out.close();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String thisline;
        StringBuilder result = new StringBuilder();
        while((thisline = reader.readLine()) != null){
            result.append(thisline);
        }
        return result.toString();
    }

}