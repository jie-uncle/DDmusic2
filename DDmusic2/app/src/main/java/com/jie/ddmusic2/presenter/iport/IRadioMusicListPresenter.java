package com.jie.ddmusic2.presenter.iport;

import com.jie.ddmusic2.javabeen.RadioList;
import com.jie.ddmusic2.javabeen.Song;

import java.util.ArrayList;

/**
 * Created by jie on 2017/7/31.
 */

public interface IRadioMusicListPresenter {
    interface view{
        void setdata(RadioList radioList);
        void iteMnusic(Song song);
        void err(String err);
    }

    interface presenter{
        void getdata();
        void getRadiomusicList(int offset, int size,String channelname);
    }

}
