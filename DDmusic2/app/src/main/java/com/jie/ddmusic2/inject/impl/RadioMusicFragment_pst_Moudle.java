package com.jie.ddmusic2.inject.impl;

import com.jie.ddmusic2.presenter.impl.NetMusicListPresenter;
import com.jie.ddmusic2.presenter.impl.RadioMusicListPresenter;
import com.jie.ddmusic2.presenter.iport.INetMusicListPresenter;
import com.jie.ddmusic2.presenter.iport.IRadioMusicListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/24.
 */
@Module
public class RadioMusicFragment_pst_Moudle {
    private IRadioMusicListPresenter.view view;

    public RadioMusicFragment_pst_Moudle(IRadioMusicListPresenter.view view) {
        this.view = view;
    }

    @Provides
    RadioMusicListPresenter providePresenter(){
        return new RadioMusicListPresenter(view);
    }
}
