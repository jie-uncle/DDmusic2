package com.jie.ddmusic2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.jie.ddmusic2.R;
import com.jie.ddmusic2.view.act.MainActivity;
import com.jie.ddmusic2.view.adapter.MainViewpagerAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/5.
 */

public class MainFragment extends BaseFragment {

    private MainFragment fragment = this;
    @BindView(R.id.fragment_main_tablayout)
    TabLayout mainToolbarTablay;
    @BindView(R.id.fragment_main_viewpager)
    ViewPager mainActivityViewpager;

    ArrayList<BaseFragment> list;


    MainViewpagerAdapter adapter;
    @BindView(R.id.main_toolbar_seek)
    ImageView mianToolbarSeek;
    @BindView(R.id.fragment_main_toolbar)
    Toolbar toolbar;

    ListenFragment listenFragment;
    LiveFragemnt liveFragemnt;
    VideoFragment videoFragment;

    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        listenFragment = new ListenFragment();
        liveFragemnt = new LiveFragemnt();
        videoFragment = new VideoFragment();
        list.add(listenFragment);
        list.add(videoFragment);
        list.add(liveFragemnt);

        adapter = new MainViewpagerAdapter(getFragmentManager(), list, getContext());
        mainActivityViewpager.setAdapter(adapter);
        mainToolbarTablay.setupWithViewPager(mainActivityViewpager);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).open_or_close(true);
            }
        });
        mianToolbarSeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
