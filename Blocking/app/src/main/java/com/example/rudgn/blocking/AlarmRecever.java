package com.example.rudgn.blocking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by rudgn on 2019-01-21.
 */

public class AlarmRecever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent in = new Intent(context, RestartService.class);
            context.startForegroundService(in);
        } else {
            Intent in = new Intent(context, RealService.class);
            context.startService(in);
        }
    }
}