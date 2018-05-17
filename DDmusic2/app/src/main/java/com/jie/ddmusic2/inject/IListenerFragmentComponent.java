package com.jie.ddmusic2.inject;

import com.jie.ddmusic2.inject.impl.LstenerFragemnt_presenter_Moudle;
import com.jie.ddmusic2.view.fragment.ListenFragment;

import dagger.Component;

/**
 * Created by jie on 2017/7/19.
 */
@Component (modules = LstenerFragemnt_presenter_Moudle.class)
public interface IListenerFragmentComponent {
    void inject(ListenFragment fragment ) ;
}
