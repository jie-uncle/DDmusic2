package com.jie.ddmusic2.inject;




import com.jie.ddmusic2.inject.impl.Welcomemoudle;
import com.jie.ddmusic2.view.act.WelcomeActivity;

import dagger.Component;

/**
 * Created by jie on 2017/7/1.
 */
@Component(modules = Welcomemoudle.class)
public interface WelcomePstComponent {
    void inject(WelcomeActivity activity);
}
