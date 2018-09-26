package com.example.user.e_leave;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by USER on 21-09-2018.
 */
public class leavenotification extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            testing();



        return START_STICKY;
    }


    public void testing(){
       while(true) {
           Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
       }
       }

}
