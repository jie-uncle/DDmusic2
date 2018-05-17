package com.jie.ddmusic2.inject.impl;

import com.jie.ddmusic2.view.fragment.MainFragment;
import com.jie.ddmusic2.view.fragment.Main_bottomFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/19.
 */
@Module
public class MainActivityMoudle {
    @Provides
    Main_bottomFragment provideMain_bottomFragment(){
        return new Main_bottomFragment();
    }

    @Provides
    MainFragment provideMainFragment() {
        return new MainFragment();
    }
}
