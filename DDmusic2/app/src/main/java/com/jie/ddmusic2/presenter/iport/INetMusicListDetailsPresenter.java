package com.jie.ddmusic2.presenter.iport;

import com.jie.ddmusic2.javabeen.Song;

/**
 * Created by jie on 2017/7/25.
 */

public interface INetMusicListDetailsPresenter {
    interface view{
        void addSong(Song song);
    }

    interface presenter{
        void getList(int type, int offset, int size);
    }
}
