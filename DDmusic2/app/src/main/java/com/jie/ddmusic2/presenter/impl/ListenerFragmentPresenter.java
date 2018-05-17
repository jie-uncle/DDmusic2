package com.jie.ddmusic2.presenter.impl;


import com.jie.ddmusic2.presenter.iport.IListeneFragmentpresenter;
import com.jie.ddmusic2.view.fragment.ListenFragment;

import javax.inject.Inject;

/**
 * Created by jie on 2017/7/5.
 */

public class ListenerFragmentPresenter implements IListeneFragmentpresenter.presenter{
    private IListeneFragmentpresenter.view view;

    public ListenerFragmentPresenter(IListeneFragmentpresenter.view view) {
        this.view = view;
    }

    @Override
    public void intent_to(int id) {
        view.intent2new(id);
    }
}
