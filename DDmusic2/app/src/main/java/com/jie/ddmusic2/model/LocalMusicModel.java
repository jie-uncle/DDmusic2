package com.jie.ddmusic2.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;


import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.Musicbeen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 2017/7/6.
 */

public class LocalMusicModel {
    private static ArrayList<Musicbeen> list=new ArrayList<>();
    //获取音频文件
    public static void getMusicFiles(Context context) {
        Cursor cursor = null;
        //通过内容提供者 查询 音频文件
        try {
            cursor = context.getContentResolver()
                    .query(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                            , null, null, null,
                            MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(cursor==null){
            return;
        }
        //装载 查询结果 的数据
        //遍历 查询结果的cursor
        while (cursor.moveToNext()) {
            //将 cursor里的数据放到实体类中
            int anInt = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            int  isMusic=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐 
            if(anInt>60*1000&&isMusic!=0){
            Musicbeen musicBean = new Musicbeen();
            musicBean.setMusic_id(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
            musicBean.setMusic_name(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            musicBean.setMusic_album(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
            musicBean.setMusic_artist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
            musicBean.setMusic_file_path(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            musicBean.setMusic_duration(anInt);
            musicBean.setMusic_size(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
            //将 实体类 添加到集合中
            list.add(musicBean);
            }
        }
        //关闭cursor
        cursor.close();

    }
    public static ArrayList<Music> getMusicList(){
        ArrayList<Music> musicList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            musicList.add(list.get(i));
        }
        return musicList;
    }
    public static ArrayList<Musicbeen> getMusicBeenList(){
        return list;
    }


}
