package com.jie.ddmusic2.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.RemoteViews;
import android.widget.TextView;


;import com.jie.ddmusic2.Manager.OnMusicChangeListener;
import com.jie.ddmusic2.Manager.PlayMessageManager;
import com.jie.ddmusic2.R;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.Musicbeen;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.model.LocalMusicModel;
import com.jie.ddmusic2.util.SPutil;
import com.jie.ddmusic2.view.act.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MusicService extends Service {
    public static final String ACTION = "com.xgj.receiver";
    public static final String MENU = "menu";
    public static final String NEXT = "next";

    public static final String PLAY = "play";
    public static final String STOP = "stop";
    public static final int ID = 103;
    private MediaPlayer my;
    private int currentListItem;
    private ArrayList<Music> list = LocalMusicModel.getMusicList();
    private boolean musicstate = false;
    int i=0;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(my.isPlaying()){
                setMusicprogress(my.getCurrentPosition());
            }
            handler.sendEmptyMessageDelayed(0,120);
        }
    };


    public void setList(ArrayList<Music> list) {
        if(list.size()==0){
            my.stop();

            setStatusChange(false);
            setMessageChange(null);
        }
        this.list = list;
    }

    public class Mybinder extends Binder {
        public MusicService getMusicService() {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Mybinder();
    }

    Notification notification;
    RemoteViews remoteView;

    @Override
    public void onCreate() {
        my = new MediaPlayer();
        RegisterReceiver();
        setNotification();

        super.onCreate();
    }


    private void setNotification() {
        //通知栏视图
        remoteView = new RemoteViews(getPackageName(), R.layout.notificationbar_layout);

        /**
         * 下一曲
         */
        Intent intent1 = new Intent();
        intent1.setAction(ACTION);//设置广播接收者
        intent1.putExtra(MENU, NEXT);//存数据
        PendingIntent next = PendingIntent.getBroadcast(this, 202, intent1, 0);

        remoteView.setOnClickPendingIntent(R.id.notificationbar_menu_music_next, next);
        /**
         * 暂停
         */
        Intent intent2 = new Intent();
        intent2.setAction(ACTION);//设置广播接收者
        intent2.putExtra(MENU, PLAY);//存数据
        PendingIntent pause = PendingIntent.getBroadcast(this, 203, intent2, 0);
        remoteView.setOnClickPendingIntent(R.id.notificationbar_menu_music_playorstop, pause);

        /**
         * 停止服务 退出程序
         */
        Intent intent4 = new Intent();
        intent4.setAction(ACTION);//设置广播接收者
        intent4.putExtra(MENU, STOP);//存数据
        PendingIntent stop = PendingIntent.getBroadcast(this, 205, intent4, 0);
        remoteView.setOnClickPendingIntent(R.id.notificationbar_menu_stopService, stop);
        /**
         * 进入主界面
         */
        setNotifationContent();
    }

    private void setNotifationContent() {
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        notification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setContent(remoteView)
                .setSmallIcon(R.drawable.music_small)
                .setWhen(System.currentTimeMillis())
                .build();
        startForeground(ID, notification);
        setStatusChange(my.isPlaying());


    }



    private void setNotificationImage() {
        if (my.isPlaying()) {
            remoteView.setImageViewResource(R.id.notificationbar_menu_music_playorstop, android.R.drawable.ic_media_pause);
        } else {
            remoteView.setImageViewResource(R.id.notificationbar_menu_music_playorstop, android.R.drawable.ic_media_play);
        }
    }


    /**
     * 注册广播接收者
     */
    private void RegisterReceiver() {
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (musicstate) {
            setMessageChange(list.get(currentListItem));
            setStatusChange(my.isPlaying());
            setMusicDuraction(my.getDuration() );
        }


        return super.onStartCommand(intent, flags, startId);
    }


    public void playMusic(ArrayList<Music> list, int position) {

        handler.removeMessages(0);
        musicstate = true;
        my.reset();
        this.list = list;
        this.currentListItem = position;
        Music music = list.get(position);
        try {
            if (music instanceof Musicbeen) {
                my.setDataSource(((Musicbeen) music).getMusic_file_path());
                remoteView.setTextViewText(R.id.notificationbar_menu_music_name, ((Musicbeen) music).getMusic_name());
                remoteView.setTextViewText(R.id.notificationbar_menu_music_singer, ((Musicbeen) music).getMusic_artist());
            } else if (music instanceof Song) {
                my.setDataSource(((Song) music).getBitrate().getFile_link());
                remoteView.setTextViewText(R.id.notificationbar_menu_music_name, ((Song) music).getSonginfo().getTitle());
                remoteView.setTextViewText(R.id.notificationbar_menu_music_singer, ((Song) music).getSonginfo().getAuthor());
            }
            setNotificationImage();
            setNotifationContent();
            setMessageChange(music);


            my.prepareAsync();

            my.setOnPreparedListener(new OnPreparedListener() {


                @Override
                public void onPrepared(MediaPlayer mp) {
                    my.start();
                    handler.sendEmptyMessageDelayed(0,120);
                    int duration = my.getDuration();
                    setMusicDuraction(duration);
                    setNotificationImage();
                    setNotifationContent();

                }
            });
            my.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    next();
                }
            });
            my.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    setBuffer(percent);

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMessageChange(Music music) {
        HashMap<String, OnMusicChangeListener> map = PlayMessageManager.getInstance().getOnMusicChangeListener();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            OnMusicChangeListener listener = map.get(iterator.next());
            listener.playMessageChange(music);
        }
    }

    private void setMusicDuraction(int duraction) {
        HashMap<String, OnMusicChangeListener> map = PlayMessageManager.getInstance().getOnMusicChangeListener();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            OnMusicChangeListener listener = map.get(iterator.next());
            listener.playDuration(duraction);
        }
    }

    private void setMusicprogress(int progress) {
        HashMap<String, OnMusicChangeListener> map = PlayMessageManager.getInstance().getOnMusicChangeListener();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            OnMusicChangeListener listener = map.get(iterator.next());
            listener.playProgress(progress);
        }
    }

    private void setBuffer(int index) {
        HashMap<String, OnMusicChangeListener> map = PlayMessageManager.getInstance().getOnMusicChangeListener();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            OnMusicChangeListener listener = map.get(iterator.next());
            listener.playBuffer(index);
        }
    }

    private void setStatusChange(boolean flag) {
        HashMap<String, OnMusicChangeListener> map = PlayMessageManager.getInstance().getOnMusicChangeListener();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            OnMusicChangeListener listener = map.get(iterator.next());
            listener.palyStatusChange(flag);
        }
    }





    public void next() {
        if (musicstate == false) {
            if (list.size() == 0) {
                return;
            }
            playMusic(list, 0);
            return;
        }
        int order = SPutil.getOrder();
        switch (order) {
            case SPutil.SEQUENTIAL:
                if (currentListItem == list.size() - 1) {
                    playMusic(list, 0);
                } else {
                    playMusic(list, currentListItem + 1);
                }
                break;
            case SPutil.RANDOM:
                int i = (int) (Math.random() * (list.size() - 1));
                playMusic(list, currentListItem);
                break;
            case SPutil.SINGLE:
                playMusic(list, currentListItem);
                break;
        }


    }



    public void upmusic() {
        if (currentListItem == 0) {
            playMusic(list, list.size() - 1);
        } else {
            playMusic(list, currentListItem - 1);
        }
    }

    public void seekto(int progress){
        my.seekTo(progress);
        my.start();
    }

    public void playorpause() {


        if (my.isPlaying()) {
            my.pause();
            handler.removeMessages(0);
        } else {
            if (musicstate == false) {
                if (list==null||list.size()==0) {
                    return;
                }
                playMusic(list, 0);

                return;
            }
            my.start();
            handler.sendEmptyMessageDelayed(0,120);
        }
        setNotificationImage();
        setNotifationContent();
    }

    public List<Music> getList() {
        return list;
    }


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String menu = intent.getStringExtra(MENU);
            switch (menu) {
                case NEXT:
                    next();
                    break;
                case PLAY:
                    playorpause();
                    break;
                case STOP:
                    my.stop();
                    stopForeground(false);
                    stopSelf();
                    android.os
                            .Process
                            .killProcess(android.os.Process.myPid());
                    break;
            }
        }
    }
}
