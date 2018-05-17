package com.jie.ddmusic2.inject.impl;

import com.jie.ddmusic2.presenter.impl.NetMusicListDetailsPresenter;
import com.jie.ddmusic2.presenter.impl.NetMusicListPresenter;
import com.jie.ddmusic2.presenter.iport.INetMusicListDetailsPresenter;
import com.jie.ddmusic2.presenter.iport.INetMusicListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/25.
 */
@Module
public class NetMusicListDetailsMoudle {
    private INetMusicListDetailsPresenter.view view;

    public NetMusicListDetailsMoudle(INetMusicListDetailsPresenter.view view) {
        this.view = view;
    }

    @Provides
    NetMusicListDetailsPresenter providesPresenter(){
        return new NetMusicListDetailsPresenter(view);
    }
}
