package com.jie.ddmusic2.inject;

import com.jie.ddmusic2.inject.impl.NetMusicDetails_Adapter_Moudle;
import com.jie.ddmusic2.inject.impl.NetMusicListDetailsMoudle;
import com.jie.ddmusic2.view.fragment.NetMusicListDetailsFragment;

import dagger.Component;

/**
 * Created by jie on 2017/7/25.
 */
@Component(modules = {NetMusicListDetailsMoudle.class, NetMusicDetails_Adapter_Moudle.class})
public interface INetMusicListDetailsComponent {
    void inject(NetMusicListDetailsFragment fragment);
}
