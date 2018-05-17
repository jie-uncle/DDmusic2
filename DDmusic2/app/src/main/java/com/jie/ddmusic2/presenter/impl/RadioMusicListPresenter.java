package com.jie.ddmusic2.presenter.impl;



import android.util.Log;

import com.jie.ddmusic2.http.HttpUtil;
import com.jie.ddmusic2.javabeen.NewMusicBean;
import com.jie.ddmusic2.javabeen.RadioItem_list;
import com.jie.ddmusic2.javabeen.RadioList;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.presenter.iport.IRadioMusicListPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jie on 2017/7/31.
 */

public class RadioMusicListPresenter implements IRadioMusicListPresenter.presenter{
    private IRadioMusicListPresenter.view view;



    public RadioMusicListPresenter(IRadioMusicListPresenter.view view) {
        this.view = view;
    }


    @Override
    public void getdata() {
        Map<String,String> map=new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.radio.getCategoryList");
        map.put("format", "json");
        HttpUtil.getInstance().getRadioList(map, new Callback<RadioList>() {
            @Override
            public void onResponse(Call<RadioList> call, Response<RadioList> response) {
                view.setdata(response.body());
            }

            @Override
            public void onFailure(Call<RadioList> call, Throwable t) {
                view.err("加载失败");
            }
        });
    }

    @Override
    public void getRadiomusicList(int offset, final int size, String channelname) {

        Map<String,String> map=new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.radio.getChannelSong");
        map.put("format", "json");
        map.put("pn", offset+"");
        map.put("rn", size+"");
        map.put("channelname", channelname);
        HttpUtil.getInstance()
                .getRadioItemList(map, new Callback<RadioItem_list>() {
                    @Override
                    public void onResponse(Call<RadioItem_list> call, Response<RadioItem_list> response) {
                        Log.e("haha",response.message()+"     zoule");
                        List<RadioItem_list.ResultBean.SonglistBean> song_list = response.body().getResult().getSonglist();
                        for (int i = 0; i <song_list.size();i++){

                            if(song_list.get(i).getSongid()!=null){

                                getmusicinfo(song_list.get(i).getSongid());
                                Log.e("haha","huoqugequxinxi");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<RadioItem_list> call, Throwable t) {

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
                view.iteMnusic(response.body());
                Log.e("haha","huoqudaole");
            }

            @Override
            public void onFailure(Call<Song> call, Throwable t) {

            }
        });
    }
}
