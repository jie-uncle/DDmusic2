package com.jie.ddmusic2.http;

import com.jie.ddmusic2.http.service.RemoteMusicService;
import com.jie.ddmusic2.javabeen.NewMusicBean;
import com.jie.ddmusic2.javabeen.RadioItem_list;
import com.jie.ddmusic2.javabeen.RadioList;
import com.jie.ddmusic2.javabeen.SeekMusicbeen;
import com.jie.ddmusic2.javabeen.Song;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jie on 2017/7/14.
 */

public class HttpUtil {
    private HttpUtil() {
    }

    private static HttpUtil httpUtils;
    private RemoteMusicService service;

    public static synchronized HttpUtil getInstance(){
        if(httpUtils==null){
            httpUtils=new HttpUtil();
        }
        return httpUtils;
    }
    private RemoteMusicService getRemoteMusicService(){
        if(service==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(RemoteMusicService.class);
        }
       return service;
    }

    public void getNewMusicbeen(Map<String ,String> map, Callback<NewMusicBean> callback){
        getRemoteMusicService();
        Call<NewMusicBean> restserverTing = service.getRestserverTing(map);
        restserverTing.enqueue(callback);
    }

    public void getSeekMusic(Map<String ,String> map, Callback<SeekMusicbeen> callback){
        getRemoteMusicService();
        Call<SeekMusicbeen> seekMusic = service.getSeekMusic(map);
        seekMusic.enqueue(callback);
    }

    public void getMusicInfo(Map<String ,String> map, Callback<Song> callback){
        getRemoteMusicService();
        Call<Song> songInfo = service.getSongInfo(map);
        songInfo.enqueue(callback);
    }

    public void getRadioList(Map<String ,String> map, Callback<RadioList> callback){
        getRemoteMusicService();
        Call<RadioList> radioList = service.getRadioList(map);
        radioList.enqueue(callback);
    }

    public void getRadioItemList(Map<String ,String> map, Callback<RadioItem_list> callback){
        getRemoteMusicService();
        Call<RadioItem_list> radioList = service.getRadioItemList(map);
        radioList.enqueue(callback);
    }
}
