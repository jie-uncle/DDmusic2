package com.jie.ddmusic2.presenter.impl;

import com.jie.ddmusic2.http.HttpUtil;
import com.jie.ddmusic2.javabeen.NewMusicBean;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.presenter.iport.INetMusicListDetailsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jie on 2017/7/25.
 */

public class NetMusicListDetailsPresenter implements INetMusicListDetailsPresenter.presenter {
    private INetMusicListDetailsPresenter.view view;

    public NetMusicListDetailsPresenter(INetMusicListDetailsPresenter.view view) {
        this.view = view;
    }

    @Override
    public void getList(int type, int offset, int size) {
        Map<String, String> map = new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.billboard.billList");
        map.put("format", "json");
        map.put("type", type + "");
        map.put("offset", offset + "");
        map.put("size", size + "");
        HttpUtil.getInstance().getNewMusicbeen(map, new Callback<NewMusicBean>() {
            @Override
            public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                ArrayList<NewMusicBean.SongListBean> song_list = response.body().getSong_list();
                for (int i = 0; i <song_list.size();i++){
                    getmusicinfo(song_list.get(i).getSong_id());
                }
            }

            @Override
            public void onFailure(Call<NewMusicBean> call, Throwable t) {

            }
        });
    }
    public void getmusicinfo(String songid) {
        Map<String, String> map = new HashMap<>();
        map.put("format", "json");
        map.put("calback", "");
        map.put("from", "webapp_music");
        map.put("method", "baidu.ting.song.play");
        map.put("songid", songid );
        HttpUtil.getInstance().getMusicInfo(map, new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                view.addSong(response.body());
            }

            @Override
            public void onFailure(Call<Song> call, Throwable t) {

            }
        });
    }
}
