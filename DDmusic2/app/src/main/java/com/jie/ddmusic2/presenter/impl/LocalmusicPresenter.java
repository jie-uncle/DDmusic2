package com.jie.ddmusic2.presenter.impl;

import android.content.Context;


import com.jie.ddmusic2.model.LocalMusicModel;
import com.jie.ddmusic2.presenter.iport.ILocalmusicPresenter;
import com.jie.ddmusic2.view.fragment.LocalMusicFragment;

import javax.inject.Inject;

/**
 * Created by jie on 2017/7/6.
 */

public class LocalmusicPresenter implements ILocalmusicPresenter.Presenter {

    ILocalmusicPresenter.View view;

    public LocalmusicPresenter(ILocalmusicPresenter.View view) {

        this.view = view;
    }

    @Override
    public void getList() {
        view.setList(LocalMusicModel.getMusicBeenList());
    }
}
