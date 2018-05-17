package com.jie.ddmusic2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.inject.DaggerIListenerFragmentComponent;
import com.jie.ddmusic2.inject.impl.LstenerFragemnt_presenter_Moudle;
import com.jie.ddmusic2.presenter.impl.ListenerFragmentPresenter;
import com.jie.ddmusic2.presenter.iport.IListeneFragmentpresenter;
import com.jie.ddmusic2.view.act.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by jie on 2017/6/29.
 */

public class ListenFragment extends BaseFragment implements IListeneFragmentpresenter.view {


    Unbinder unbinder;
    @BindView(R.id.fragment_listener_iv_localmusic)
    LinearLayout fragmentListenerIvLocalmusic;
    @BindView(R.id.fragment_listener_iv_like)
    LinearLayout fragmentListenerIvLike;
    @BindView(R.id.fragment_listener_iv_download)
    LinearLayout fragmentListenerIvDownload;
    @BindView(R.id.fragment_listener_iv_recently)
    LinearLayout fragmentListenerIvRecently;
    @BindView(R.id.fragment_listener_musiclist)
    LinearLayout fragmentListenerMusiclist;

    @Inject
    ListenerFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerIListenerFragmentComponent.builder()
                .lstenerFragemnt_presenter_Moudle(new LstenerFragemnt_presenter_Moudle(this))
                .build()
                .inject(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void intent2new(int id) {
        switch (id) {
            case R.id.fragment_listener_iv_localmusic:
                ((MainActivity) getActivity()).addFragment(new LocalMusicFragment(), true, this);
                break;
            case R.id.fragment_listener_iv_like:
                break;
            case R.id.fragment_listener_iv_download:
                break;
            case R.id.fragment_listener_iv_recently:
                break;
            case R.id.fragment_listener_musiclist:
                ((MainActivity) getActivity()).addFragment(new NetMusicListFragment(), true, this);
                break;
            case R.id.fragment_listener_radiolist:
                ((MainActivity) getActivity()).addFragment(new RadioMusicListFragment(), true, this);
                break;
        }
    }

    @OnClick({R.id.fragment_listener_iv_localmusic, R.id.fragment_listener_radiolist, R.id.fragment_listener_iv_like, R.id.fragment_listener_iv_download, R.id.fragment_listener_iv_recently, R.id.fragment_listener_musiclist})
    public void onViewClicked(View view) {
        int id = 0;
        switch (view.getId()) {
            case R.id.fragment_listener_iv_localmusic:
                id = R.id.fragment_listener_iv_localmusic;
                break;
            case R.id.fragment_listener_iv_like:
                id = R.id.fragment_listener_iv_like;
                break;
            case R.id.fragment_listener_iv_download:
                id = R.id.fragment_listener_iv_download;
                break;
            case R.id.fragment_listener_iv_recently:
                id = R.id.fragment_listener_iv_recently;
                break;
            case R.id.fragment_listener_musiclist:
                id = R.id.fragment_listener_musiclist;
                break;
            case R.id.fragment_listener_radiolist:
                id = R.id.fragment_listener_radiolist;
                break;

        }
        presenter.intent_to(id);
    }
}
