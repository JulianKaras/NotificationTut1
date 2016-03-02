package com.barkerville.notificationtut1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by JulianK on 01/03/2016.
 */
public class Alarm {

    public void setAlarm(Context context) {

        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //alarmMgr.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 10 * 1000,
        // getMainActivityPendingIntent());
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 10*1000,
        10*1000, getMainActivityPendingIntent(context));
       // alarmMgr.setInexactRepeating(AlarmManager.RTC, milliseconds, AlarmManager.INTERVAL_DAY,
               // getMainActivityPendingIntent());

        ComponentName bootReceiver = new ComponentName(context, BootReceiver.class);
        PackageManager packageManager = context.getPackageManager();
        packageManager.setComponentEnabledSetting(bootReceiver,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
               PackageManager.DONT_KILL_APP );

       /* Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 42);
        long milliseconds = calendar.getTimeInMillis();*/

        Log.i("alarm", "set alarm");


    }


    public void cancelAlarm(Context context){
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.cancel(getMainActivityPendingIntent(context));

        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Log.i("alarm", "Cancel alarm");


    }

    protected PendingIntent getMainActivityPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return (pendingIntent);
    }



}
