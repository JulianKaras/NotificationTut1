package com.barkerville.notificationtut1;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private int notificationID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alarmButton = (Button)findViewById(R.id.alarm_set);
        alarmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setAlarm();
            }
        });



        Button cancelAlarmButton = (Button)findViewById(R.id.alarm_cancel);
        cancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v){
                cancelAlarm();
            }
        });




        Button notificationButton = (Button) findViewById(R.id.notification);


        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayNotification();
            }
        });

    }

    public void setAlarm() {

       Alarm alarm = new Alarm();
        alarm.setAlarm(this);


    }
    public void cancelAlarm(){

       Alarm alarm = new Alarm();
        alarm.cancelAlarm(this);
    }


    protected void displayNotification() {

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        Log.i("Notify", "Displaying notification");
        nBuilder.setContentTitle("Notification");
        nBuilder.setContentText("This is a notification");
        nBuilder.setSmallIcon(R.drawable.ic_launcher);
        nBuilder.setAutoCancel(true);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(pendingIntent);



        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, nBuilder.build());
    }


   protected PendingIntent getMainActivityPendingIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return (pendingIntent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

