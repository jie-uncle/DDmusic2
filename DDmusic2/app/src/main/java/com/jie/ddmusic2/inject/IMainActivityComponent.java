package com.jie.ddmusic2.inject;

import com.jie.ddmusic2.inject.impl.MainActivityMoudle;
import com.jie.ddmusic2.view.act.MainActivity;

import dagger.Component;

/**
 * Created by jie on 2017/7/19.
 */
@Component(modules = MainActivityMoudle.class)
public interface IMainActivityComponent {
    void inject(MainActivity activity);
}
