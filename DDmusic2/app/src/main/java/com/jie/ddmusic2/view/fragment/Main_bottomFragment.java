package com.jie.ddmusic2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jie.ddmusic2.R;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.Musicbeen;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.view.act.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/5.
 */

public class Main_bottomFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.main_bottom_menu_music_pic_small)
    ImageView mainBottomMenuMusicPicSmall;
    @BindView(R.id.main_bottom_menu_music_seekbar)
    SeekBar mainBottomMenuMusicSeekbar;
    @BindView(R.id.main_bottom_menu_music_name)
    TextView mainBottomMenuMusicName;

    @BindView(R.id.main_bottom_menu_music_singer)
    TextView mainBottomMenuMusicSinger;
    @BindView(R.id.main_bottom_menu_iv_play)
    ImageView mainBottomMenuIvPlay;

    @BindView(R.id.main_bottom_menu_iv_next)
    ImageView mainBottomMenuIvNext;
    @BindView(R.id.main_bottom_menu_music_list)
    ImageView mainBottomMenuMusicList;
    Unbinder unbinder;

    MainActivity activity;
    @BindView(R.id.main_bottom_menu_lay)
    LinearLayout mainBottomMenuLay;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemnt_main_bottom, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MainActivity) getActivity();
        mainBottomMenuMusicSeekbar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.main_bottom_menu_iv_play, R.id.main_bottom_menu_iv_next, R.id.main_bottom_menu_music_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_bottom_menu_iv_play:
                ((MainActivity) getActivity()).play_or_pauseMusic();
                break;
            case R.id.main_bottom_menu_iv_next:
                ((MainActivity) getActivity()).nextMusic();
                break;
            case R.id.main_bottom_menu_music_list:

                break;
        }
    }

    public void setMessage(Music music) {
        if (music==null){
            mainBottomMenuMusicName.setText("歌名");
            mainBottomMenuMusicSinger.setText("歌手");
        }else if (music instanceof Musicbeen) {
            Musicbeen musicbeen = (Musicbeen) music;
            mainBottomMenuMusicName.setText(musicbeen.getMusic_name());
            mainBottomMenuMusicSinger.setText(musicbeen.getMusic_artist());
        } else if (music instanceof Song) {
            Song song = (Song) music;
            mainBottomMenuMusicName.setText(song.getSonginfo().getTitle());
            mainBottomMenuMusicSinger.setText(song.getSonginfo().getAuthor());
            Glide.with(getActivity()).load(song.getSonginfo().getPic_premium()).into(mainBottomMenuMusicPicSmall);
        }
    }

    public void setPlayStatus(boolean flag) {
        if (flag) {
            mainBottomMenuIvPlay.setImageResource(android.R.drawable.ic_media_pause);
        } else {
            mainBottomMenuIvPlay.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    public void setbufferStatus(int schedule) {
        mainBottomMenuMusicSeekbar.setSecondaryProgress(schedule);
    }

    public void setSeekBar_Progress( int progress) {
        mainBottomMenuMusicSeekbar.setProgress(progress);

    }
    public void setSeekbarDuraction(int max){
        mainBottomMenuMusicSeekbar.setMax(max);
    }


    @OnClick(R.id.main_bottom_menu_lay)
    public void onViewClicked() {
        ((MainActivity) getActivity()).intent2MusicPlay();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        ((MainActivity)getActivity()).seekto(seekBar.getProgress());
    }
}
