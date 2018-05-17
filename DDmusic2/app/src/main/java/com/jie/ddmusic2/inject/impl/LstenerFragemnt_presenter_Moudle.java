package com.jie.ddmusic2.inject.impl;

import com.jie.ddmusic2.presenter.impl.ListenerFragmentPresenter;
import com.jie.ddmusic2.presenter.iport.IListeneFragmentpresenter;
import com.jie.ddmusic2.view.fragment.ListenFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/19.
 */
@Module
public class LstenerFragemnt_presenter_Moudle {
    private IListeneFragmentpresenter.view view;

    public LstenerFragemnt_presenter_Moudle(IListeneFragmentpresenter.view view) {
        this.view = view;
    }

    @Provides
    ListenerFragmentPresenter providesView(){
        return new ListenerFragmentPresenter(view);
    }
}
