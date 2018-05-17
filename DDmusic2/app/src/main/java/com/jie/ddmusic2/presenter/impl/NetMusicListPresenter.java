package com.jie.ddmusic2.presenter.impl;

import android.util.Log;

import com.jie.ddmusic2.http.HttpUtil;
import com.jie.ddmusic2.javabeen.NewMusicBean;
import com.jie.ddmusic2.presenter.iport.INetMusicListPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jie on 2017/7/24.
 */

public class NetMusicListPresenter implements INetMusicListPresenter.presenter{
    private INetMusicListPresenter.view view;

    public NetMusicListPresenter(INetMusicListPresenter.view view) {
        this.view = view;
    }

    @Override
    public void getList() {
        final ArrayList<NewMusicBean> list=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.billboard.billList");
        map.put("format", "json");
        map.put("type", "1");
        map.put("offset", "0");
        map.put("size", "3");
//        新歌榜
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
//        热歌榜
        map.put("type", "2");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
//        摇滚榜
        map.put("type", "11");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });


//        欧美金曲榜
        map.put("type", "21");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
        //经典老歌榜
        map.put("type", "22");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
        //情歌对唱榜
        map.put("type", "23");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
        //影视金曲榜
        map.put("type", "24");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
        //网络歌曲榜
        map.put("type", "25");
        HttpUtil.getInstance()
                .getNewMusicbeen(map, new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        list.add(response.body());
                        Log.e("haha",list.size()+"");
                        if(list.size()==8){
                            view.setList(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {
                        view.err(t.getMessage());
                    }
                });
    }
}
