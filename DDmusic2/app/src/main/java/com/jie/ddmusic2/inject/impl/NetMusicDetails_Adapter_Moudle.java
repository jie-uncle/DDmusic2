package com.jie.ddmusic2.inject.impl;

import android.content.Context;

import com.jie.ddmusic2.javabeen.Song;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/25.
 */
@Module
public class NetMusicDetails_Adapter_Moudle {
    private Context context;
    private ArrayList<Song> list;

    public NetMusicDetails_Adapter_Moudle(Context context, ArrayList<Song> list) {
        this.context = context;
        this.list = list;
    }

    @Provides
    Context provideContext(){
        return context;
    }

    @Provides
    ArrayList<Song> provideArrayList(){
        return list;
    }
}
