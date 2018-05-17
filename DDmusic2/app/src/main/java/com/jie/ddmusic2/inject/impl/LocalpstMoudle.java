package com.jie.ddmusic2.inject.impl;

import android.content.Context;


import com.jie.ddmusic2.presenter.impl.LocalmusicPresenter;
import com.jie.ddmusic2.presenter.iport.ILocalmusicPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/6.
 */
@Module
public class LocalpstMoudle {

    private ILocalmusicPresenter.View view;

    public LocalpstMoudle( ILocalmusicPresenter.View view) {

        this.view = view;
    }


    @Provides
    LocalmusicPresenter getfragment(){
        return new LocalmusicPresenter(view);
    }
}
