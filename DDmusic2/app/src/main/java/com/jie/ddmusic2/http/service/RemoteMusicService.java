package com.jie.ddmusic2.http.service;

import com.jie.ddmusic2.http.API;
import com.jie.ddmusic2.javabeen.NewMusicBean;
import com.jie.ddmusic2.javabeen.RadioItem_list;
import com.jie.ddmusic2.javabeen.RadioList;
import com.jie.ddmusic2.javabeen.SeekMusicbeen;
import com.jie.ddmusic2.javabeen.Song;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by jie on 2017/7/14.
 */

public interface RemoteMusicService {
    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
        @GET(API.ACTION_TING)
    Call<NewMusicBean>  getRestserverTing(@QueryMap Map<String,String> map);

    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<Song> getSongInfo(@QueryMap Map<String, String> map);

    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<SeekMusicbeen> getSeekMusic(@QueryMap Map<String,String> map);

    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<RadioList> getRadioList(@QueryMap Map<String, String> map);

    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<RadioItem_list> getRadioItemList(@QueryMap Map<String, String> map);
}
