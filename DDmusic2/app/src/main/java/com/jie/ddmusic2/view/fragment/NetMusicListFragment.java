package com.jie.ddmusic2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.inject.DaggerINetMusicListFragmentComponent;
import com.jie.ddmusic2.inject.impl.NetMusicFragment_pst_Moudle;
import com.jie.ddmusic2.javabeen.NewMusicBean;
import com.jie.ddmusic2.presenter.impl.NetMusicListPresenter;
import com.jie.ddmusic2.presenter.iport.INetMusicListPresenter;
import com.jie.ddmusic2.util.Final;
import com.jie.ddmusic2.view.act.MainActivity;
import com.jie.ddmusic2.view.adapter.NetMusicListAdapter;
import com.jie.ddmusic2.view.adapter.OnItenclickListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/24.
 */

public class NetMusicListFragment extends BaseFragment implements INetMusicListPresenter.view {
    @Inject
    NetMusicListPresenter presenter;
    @BindView(R.id.fragment_musiclist_recycleview)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.fragment_net_musiclist_back)
    ImageView fragmentNetMusiclistBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net_musiclist, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerINetMusicListFragmentComponent.builder()
                .netMusicFragment_pst_Moudle(new NetMusicFragment_pst_Moudle(this))
                .build()
                .inject(this);
        presenter.getList();
    }

    @Override
    public void setList(final ArrayList<NewMusicBean> list) {
        Log.e("haha", list.size() + "");
        NetMusicListAdapter adapter = new NetMusicListAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnClickListener(new OnItenclickListener() {
            @Override
            public void OnitemclickListener(int position) {
                if (list.get(position) == null) {
                    return;
                }
                String Mtype = list.get(position).getBillboard().getBillboard_type();
                int type = 0;
                switch (Mtype) {
                    case Final.API_NEW_MUSIC + "":
                        type = Final.API_NEW_MUSIC;
                        break;
                    case Final.API_HOT_MUSIC + "":
                        type = Final.API_HOT_MUSIC;
                        break;
                    case Final.API_ROCK_MUSIC + "":
                        type = Final.API_ROCK_MUSIC;
                        break;
                    case Final.API_E_AND_A_MUSIC + "":
                        type = Final.API_E_AND_A_MUSIC;
                        break;
                    case Final.API_SUTRA_MUSIC + "":
                        type = Final.API_SUTRA_MUSIC;
                        break;
                    case Final.API_LOVE_MUSIC + "":
                        type = Final.API_LOVE_MUSIC;
                        break;
                    case Final.API_MOVIE_MUSIC + "":
                        type = Final.API_MOVIE_MUSIC;
                        break;
                    case Final.API_NET_MUSIC + "":
                        type = Final.API_NET_MUSIC;
                        break;
                }

                ((MainActivity) getActivity()).intent2details(type, NetMusicListFragment.this);
            }

            @Override
            public void OnitemLongclickListener(int position) {

            }

            @Override
            public void OnMenuclickListener(int position) {

            }
        });
    }

    @Override
    public void err(String err) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_net_musiclist_back)
    public void onViewClicked() {
        ((MainActivity) getActivity()).onback();
    }
}
