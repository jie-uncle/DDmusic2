package com.jie.ddmusic2.presenter.iport;


import com.jie.ddmusic2.javabeen.Musicbeen;

import java.util.ArrayList;

/**
 * Created by jie on 2017/7/6.
 */

public interface ILocalmusicPresenter {
    interface View{
        void setList(ArrayList<Musicbeen> list);
    }
    interface Presenter{
        void getList();
    }
}
