package com.jie.ddmusic2.Manager;

import java.util.HashMap;

/**
 * Created by jie on 2017/7/19.
 */

public class PlayMessageManager {
    private PlayMessageManager() {
    }
    private HashMap<String,OnMusicChangeListener> map=new HashMap<>();


    private static PlayMessageManager manager;


    public static synchronized PlayMessageManager getInstance(){
        if(manager==null){
            manager=new PlayMessageManager();
        }
        return manager;
    }

    public HashMap<String, OnMusicChangeListener> getOnMusicChangeListener() {
        return map;
    }

    public void setOnMusicChangeListener(String key,OnMusicChangeListener listener) {
        map.put(key,listener);
    }

    public void removeListener(String key){
       map.remove(key);
    }


}
