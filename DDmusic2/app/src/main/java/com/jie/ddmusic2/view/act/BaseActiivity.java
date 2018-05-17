package com.jie.ddmusic2.view.act;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jie.ddmusic2.service.MusicService;

/**
 * Created by jie on 2017/7/13.
 */

public class BaseActiivity extends AppCompatActivity {

    public MusicService service;
    ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService();
    }

    private void startService() {
        Intent i = new Intent(this, MusicService.class);

         conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                    BaseActiivity.this.service= ((MusicService.Mybinder)service).getMusicService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        startService(i);
        bindService(i, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
