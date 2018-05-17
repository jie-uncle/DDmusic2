package com.jie.ddmusic2.presenter.impl;

import android.os.Handler;
import android.os.Message;


import com.jie.ddmusic2.presenter.iport.IWelcomePresenter;

import javax.inject.Inject;

/**
 * Created by jie on 2017/6/28.
 */

public class WelcomePresenter implements IWelcomePresenter.WelcomePresenter {
    public  final int MSG_WHAT = 101;
    public  final int DELAY_MILLIS = 3000;
    private  IWelcomePresenter.WelcomeView welcome;
    public  Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            welcome.intent2main();
        }
    };

    public WelcomePresenter(IWelcomePresenter.WelcomeView welcome) {
        this.welcome = welcome;
    }

    @Override
    public void time() {
        handler.sendEmptyMessageDelayed(MSG_WHAT, DELAY_MILLIS);
    }

    @Override
    public void click() {
        handler.removeMessages(MSG_WHAT);
        welcome.intent2main();
    }
}
