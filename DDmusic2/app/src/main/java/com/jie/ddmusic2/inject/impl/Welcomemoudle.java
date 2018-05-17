package com.jie.ddmusic2.inject.impl;



import com.jie.ddmusic2.presenter.impl.WelcomePresenter;
import com.jie.ddmusic2.presenter.iport.IWelcomePresenter;
import com.jie.ddmusic2.view.act.WelcomeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jie on 2017/7/4.
 */
@Module
public class Welcomemoudle {
    IWelcomePresenter.WelcomeView welcomeActivity;

    public Welcomemoudle(IWelcomePresenter.WelcomeView welcomeActivity) {
        this.welcomeActivity = welcomeActivity;
    }

    @Provides
    WelcomePresenter provideWelcomepst(){
        return new WelcomePresenter(welcomeActivity);
    }
}
