package com.jie.ddmusic2.javabeen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jie on 2017/7/13.
 */

public class Musicbeen extends Music implements Parcelable {
    private String music_id;//ID
    private String music_name;//歌曲名
    private String music_album;//专辑名
    private String music_artist;//歌手名
    private String music_file_path;//文件路径
    private int music_duration;//歌曲时长
    private int music_size;//文件大小
    private boolean music_my_love = false;//我喜欢
    private String music_icon_path_small ;//小歌曲图片地址
    private String music_icon_path_big ;//大歌曲图片地址

    public Musicbeen() {
    }

    protected Musicbeen(Parcel in) {
        music_id = in.readString();
        music_name = in.readString();
        music_album = in.readString();
        music_artist = in.readString();
        music_file_path = in.readString();
        music_duration = in.readInt();
        music_size = in.readInt();
        music_my_love = in.readByte() != 0;
        music_icon_path_small = in.readString();
        music_icon_path_big = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(music_id);
        dest.writeString(music_name);
        dest.writeString(music_album);
        dest.writeString(music_artist);
        dest.writeString(music_file_path);
        dest.writeInt(music_duration);
        dest.writeInt(music_size);
        dest.writeByte((byte) (music_my_love ? 1 : 0));
        dest.writeString(music_icon_path_small);
        dest.writeString(music_icon_path_big);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Musicbeen> CREATOR = new Creator<Musicbeen>() {
        @Override
        public Musicbeen createFromParcel(Parcel in) {
            return new Musicbeen(in);
        }

        @Override
        public Musicbeen[] newArray(int size) {
            return new Musicbeen[size];
        }
    };

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getMusic_album() {
        return music_album;
    }

    public void setMusic_album(String music_album) {
        this.music_album = music_album;
    }

    public String getMusic_artist() {
        return music_artist;
    }

    public void setMusic_artist(String music_artist) {
        this.music_artist = music_artist;
    }

    public String getMusic_file_path() {
        return music_file_path;
    }

    public void setMusic_file_path(String music_file_path) {
        this.music_file_path = music_file_path;
    }

    public int getMusic_duration() {
        return music_duration;
    }

    public void setMusic_duration(int music_duration) {
        this.music_duration = music_duration;
    }

    public int getMusic_size() {
        return music_size;
    }

    public void setMusic_size(int music_size) {
        this.music_size = music_size;
    }

    public boolean isMusic_my_love() {
        return music_my_love;
    }

    public void setMusic_my_love(boolean music_my_love) {
        this.music_my_love = music_my_love;
    }

    public String getMusic_icon_path_small() {
        return music_icon_path_small;
    }

    public void setMusic_icon_path_small(String music_icon_path_small) {
        this.music_icon_path_small = music_icon_path_small;
    }

    public String getMusic_icon_path_big() {
        return music_icon_path_big;
    }

    public void setMusic_icon_path_big(String music_icon_path_big) {
        this.music_icon_path_big = music_icon_path_big;
    }
}
