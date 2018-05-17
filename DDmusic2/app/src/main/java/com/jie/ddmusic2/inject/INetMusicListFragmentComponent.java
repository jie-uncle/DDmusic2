package com.jie.ddmusic2.inject;

import com.jie.ddmusic2.inject.impl.NetMusicFragment_pst_Moudle;
import com.jie.ddmusic2.view.fragment.NetMusicListFragment;

import dagger.Component;

/**
 * Created by jie on 2017/7/24.
 */
@Component(modules = NetMusicFragment_pst_Moudle.class)
public interface INetMusicListFragmentComponent {
    void inject(NetMusicListFragment fragment);
}
