package com.jie.ddmusic2.inject.impl;

import com.jie.ddmusic2.presenter.impl.NetMusicListPresenter;
import com.jie.ddmusic2.presenter.iport.INetMusicListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/24.
 */
@Module
public class NetMusicFragment_pst_Moudle {
    private INetMusicListPresenter.view view;

    public NetMusicFragment_pst_Moudle(INetMusicListPresenter.view view) {
        this.view = view;
    }

    @Provides
    NetMusicListPresenter providePresenter(){
        return new NetMusicListPresenter(view);
    }
}
