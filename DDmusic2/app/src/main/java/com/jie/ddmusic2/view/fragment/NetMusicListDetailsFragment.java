package com.jie.ddmusic2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.inject.DaggerINetMusicListDetailsComponent;
import com.jie.ddmusic2.inject.impl.NetMusicDetails_Adapter_Moudle;
import com.jie.ddmusic2.inject.impl.NetMusicListDetailsMoudle;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.presenter.impl.NetMusicListDetailsPresenter;
import com.jie.ddmusic2.presenter.iport.INetMusicListDetailsPresenter;
import com.jie.ddmusic2.util.Final;
import com.jie.ddmusic2.view.act.MainActivity;
import com.jie.ddmusic2.view.adapter.NetMusicListDetilAdapter;
import com.jie.ddmusic2.view.adapter.OnItenclickListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/25.
 */

public class NetMusicListDetailsFragment extends BaseFragment implements INetMusicListDetailsPresenter.view {

    @Inject
    NetMusicListDetailsPresenter presenter;
    @Inject
    NetMusicListDetilAdapter adapter;

    ArrayList<Music> musicArrayList = new ArrayList<>();
    ArrayList<Song> list = new ArrayList<>();
    @BindView(R.id.music_list_details_title)
    TextView musicListDetailsTitle;
    @BindView(R.id.fragment_localmusc_toolbar)
    Toolbar fragmentLocalmuscToolbar;
    @BindView(R.id.music_list_details_recycleview)
    RecyclerView musicListDetailsRecycleview;
    Unbinder unbinder;
    @BindView(R.id.fragment_net_musiclist_details_back)
    ImageView fragmentNetMusiclistDetailsBack;

    private int type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net_music_list_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerINetMusicListDetailsComponent.builder()
                .netMusicListDetailsMoudle(new NetMusicListDetailsMoudle(this))
                .netMusicDetails_Adapter_Moudle(new NetMusicDetails_Adapter_Moudle(getContext(), list))
                .build()
                .inject(this);
        type = getArguments().getInt(Final.API_MUSIC_TYPE);
        presenter.getList(type, 0, 50);
        setTital();
        musicListDetailsRecycleview.setAdapter(adapter);
        musicListDetailsRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setClick(new OnItenclickListener() {
            @Override
            public void OnitemclickListener(int position) {
                ((MainActivity) getActivity()).playMusic(musicArrayList, position);
            }

            @Override
            public void OnitemLongclickListener(int position) {

            }

            @Override
            public void OnMenuclickListener(int position) {

            }
        });
    }

    private void setTital() {
        switch (type) {
            case Final.API_NEW_MUSIC:
                musicListDetailsTitle.setText("新歌榜");
                break;
            case Final.API_HOT_MUSIC:
                musicListDetailsTitle.setText("热歌榜");
                break;
            case Final.API_ROCK_MUSIC:
                musicListDetailsTitle.setText("摇滚榜");
                break;
            case Final.API_E_AND_A_MUSIC:
                musicListDetailsTitle.setText("欧美金曲榜");
                break;
            case Final.API_SUTRA_MUSIC:
                musicListDetailsTitle.setText("经典老歌榜");
                break;
            case Final.API_LOVE_MUSIC:
                musicListDetailsTitle.setText("情歌对唱榜");
                break;
            case Final.API_MOVIE_MUSIC:
                musicListDetailsTitle.setText("影视金曲榜");
                break;
            case Final.API_NET_MUSIC:
                musicListDetailsTitle.setText("网络歌曲榜");
                break;
        }
    }


    @Override
    public void addSong(Song song) {
        list.add(song);
        musicArrayList.add(song);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_net_musiclist_details_back)
    public void onViewClicked() {
        ((MainActivity) getActivity()).onback();
    }
}
