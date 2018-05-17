package com.jie.ddmusic2.Manager;

import com.jie.ddmusic2.javabeen.Music;

/**
 * Created by jie on 2017/7/19.
 */

public interface OnMusicChangeListener {
    void playMessageChange(Music music);
    void palyStatusChange(boolean flag);
    void playBuffer(int schedule);
    void playProgress(int progress);
    void playDuration(int duraction);
}
