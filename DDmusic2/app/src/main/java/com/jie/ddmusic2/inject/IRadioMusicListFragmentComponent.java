package com.jie.ddmusic2.inject;

import com.jie.ddmusic2.inject.impl.NetMusicFragment_pst_Moudle;
import com.jie.ddmusic2.inject.impl.RadioMusicFragment_pst_Moudle;
import com.jie.ddmusic2.view.fragment.NetMusicListFragment;
import com.jie.ddmusic2.view.fragment.RadioMusicListFragment;

import dagger.Component;

/**
 * Created by jie on 2017/7/24.
 */
@Component(modules = RadioMusicFragment_pst_Moudle.class)
public interface IRadioMusicListFragmentComponent {
    void inject(RadioMusicListFragment fragment);
}
