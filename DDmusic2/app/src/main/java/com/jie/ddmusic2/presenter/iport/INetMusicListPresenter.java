package com.jie.ddmusic2.presenter.iport;

import com.jie.ddmusic2.javabeen.NewMusicBean;

import java.util.ArrayList;

/**
 * Created by jie on 2017/7/24.
 */

public interface INetMusicListPresenter {
    interface view{
        void setList(ArrayList<NewMusicBean> list);
        void err(String err);
    }

    interface presenter{
        void getList();
    }
}
