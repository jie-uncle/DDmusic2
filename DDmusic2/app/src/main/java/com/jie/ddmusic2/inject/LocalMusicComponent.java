package com.jie.ddmusic2.inject;



import com.jie.ddmusic2.inject.impl.LocalpstMoudle;
import com.jie.ddmusic2.view.fragment.LocalMusicFragment;

import dagger.Component;

/**
 * Created by jie on 2017/7/6.
 */
@Component(modules = {LocalpstMoudle.class})
public interface LocalMusicComponent {
    void inject(LocalMusicFragment fragment);
}
