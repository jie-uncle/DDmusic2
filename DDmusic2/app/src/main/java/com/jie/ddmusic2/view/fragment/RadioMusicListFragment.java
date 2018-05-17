package com.jie.ddmusic2.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.ScrollRecyclerLayoutManager;
import com.jie.ddmusic2.ScrollViewTabIndicator;
import com.jie.ddmusic2.inject.DaggerIRadioMusicListFragmentComponent;
import com.jie.ddmusic2.inject.impl.RadioMusicFragment_pst_Moudle;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.RadioList;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.presenter.impl.RadioMusicListPresenter;
import com.jie.ddmusic2.presenter.iport.IRadioMusicListPresenter;
import com.jie.ddmusic2.util.ToastUtil;
import com.jie.ddmusic2.view.act.MainActivity;
import com.jie.ddmusic2.view.adapter.OnItenclickListener;
import com.jie.ddmusic2.view.adapter.OnRadioItemclickListener;
import com.jie.ddmusic2.view.adapter.RadioListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/28.
 */

public class RadioMusicListFragment extends BaseFragment implements IRadioMusicListPresenter.view, NestedScrollView.OnScrollChangeListener, OnRadioItemclickListener {
    public static final String TUIJIAN = "推荐频道";
    public static final String SHIGUANG = "时光频道";
    public static final String FENGGE = "风格频道";
    public static final String XINQING = "心情频道";
    public static final String YUZHONG = "语种频道";
    @Inject
    RadioMusicListPresenter presenter;
    @BindView(R.id.fragment_loadMusic_back)
    ImageView Back;
    @BindView(R.id.fragment_radiomusc_toolbar)
    android.support.v7.widget.Toolbar Toolbar;
    @BindView(R.id.tab2)
    ScrollViewTabIndicator tab2;
    @BindView(R.id.fragment_radio_tuijian_recyclerview)
    RecyclerView TuijianRecyclerview;
    @BindView(R.id.fragment_radio_view1)
    LinearLayout View1;
    @BindView(R.id.fragment_radio_shiguang_recyclerview)
    RecyclerView ShiguangRecyclerview;
    @BindView(R.id.fragment_radio_view2)
    LinearLayout View2;
    @BindView(R.id.fragment_radio_fengge_recyclerview)
    RecyclerView FenggeRecyclerview;
    @BindView(R.id.fragment_radio_view3)
    LinearLayout View3;
    @BindView(R.id.fragment_radio_xinqing_recyclerview)
    RecyclerView XinqingRecyclerview;
    @BindView(R.id.fragment_radio_view4)
    LinearLayout View4;
    @BindView(R.id.fragment_radio_yuzhong_recyclerview)
    RecyclerView YuzhongRecyclerview;
    @BindView(R.id.fragment_radio_view5)
    LinearLayout View5;
    @BindView(R.id.sv)
    NestedScrollView sv;
    @BindView(R.id.tab)
    ScrollViewTabIndicator tab;
    @BindView(R.id.fragment_radio_progressbar_lay)
    LinearLayout ProgressbarLay;
    Unbinder unbinder;
    private RadioListAdapter tuijianAdapter, shiguanAdapter, fenggeAdapter, xinqingAdapter, yuzhongAdapter;
    private int[] mTabMiddleLocation = new int[2];
    private int[] mTabTopLocation = new int[2];
    private ArrayList<RadioList.ResultBean.ChannellistBean>
            tuijian = new ArrayList<>()
            , shiguang = new ArrayList<>()
            , fengge = new ArrayList<>()
            , xinqing = new ArrayList<>()
            , yuzhong = new ArrayList<>();


    ArrayList<Music> musiclist=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerIRadioMusicListFragmentComponent.builder()
                .radioMusicFragment_pst_Moudle(new RadioMusicFragment_pst_Moudle(this))
                .build()
                .inject(this);
        List<String> names = new ArrayList<>();
        names.add("推荐");
        names.add("时光");
        names.add("风格");
        names.add("心情");
        names.add("语种");
        List<View> views = new ArrayList<>();
        views.add(View1);
        views.add(View2);
        views.add(View3);
        views.add(View4);
        views.add(View5);


        tab.setScrollView(sv,this,names,views);
        //将mTab本身作为参数传入mTab2已达到同步状态
        tab2.setScrollView(sv,tab,names,views);
        presenter.getdata();

        tuijianAdapter = new RadioListAdapter(tuijian, getContext(),TuijianRecyclerview);
        shiguanAdapter = new RadioListAdapter(shiguang, getContext(),ShiguangRecyclerview);
        fenggeAdapter = new RadioListAdapter(fengge, getContext(),FenggeRecyclerview);
        xinqingAdapter = new RadioListAdapter(xinqing, getContext(),XinqingRecyclerview);
        yuzhongAdapter = new RadioListAdapter(yuzhong, getContext(),YuzhongRecyclerview);

        TuijianRecyclerview.setAdapter(tuijianAdapter);
        TuijianRecyclerview.setLayoutManager(new ScrollRecyclerLayoutManager(getContext()));
        tuijianAdapter.setOnClickListener(this);

        ShiguangRecyclerview.setAdapter(shiguanAdapter);
        ShiguangRecyclerview.setLayoutManager(new ScrollRecyclerLayoutManager(getContext()));
        shiguanAdapter.setOnClickListener(this);

        FenggeRecyclerview.setAdapter(fenggeAdapter);
        FenggeRecyclerview.setLayoutManager(new ScrollRecyclerLayoutManager(getContext()));
        fenggeAdapter.setOnClickListener(this);

        XinqingRecyclerview.setAdapter(xinqingAdapter);
        XinqingRecyclerview.setLayoutManager(new ScrollRecyclerLayoutManager(getContext()));
        xinqingAdapter.setOnClickListener(this);

        YuzhongRecyclerview.setAdapter(yuzhongAdapter);
        YuzhongRecyclerview.setLayoutManager(new ScrollRecyclerLayoutManager(getContext()));
        yuzhongAdapter.setOnClickListener(this);
    }

    @Override
    public void setdata(final RadioList radioList) {
        ProgressbarLay.setVisibility(View.GONE);
        final List<RadioList.ResultBean.ChannellistBean> channellist = radioList.getResult().get(0).getChannellist();

        for (RadioList.ResultBean.ChannellistBean been : channellist) {
            switch (been.getCate_sname()) {
                case TUIJIAN:
                    tuijian.add(been);
                    break;
                case SHIGUANG:
                    shiguang.add(been);
                    break;
                case FENGGE:
                    fengge.add(been);
                    break;
                case XINQING:
                    xinqing.add(been);
                    break;
                case YUZHONG:
                    yuzhong.add(been);
                    break;
            }
        }
        tuijianAdapter.notifyDataSetChanged();
        shiguanAdapter.notifyDataSetChanged();
        fenggeAdapter.notifyDataSetChanged();
        xinqingAdapter.notifyDataSetChanged();
        yuzhongAdapter.notifyDataSetChanged();

//            adapter.setOnClickListener(new OnRadioItemclickListener() {
//                @Override
//                public void OnitemClicklistener(int position, View view) {
//                    musiclist.clear();
//                    ((MainActivity)getActivity()).setList(musiclist);
//                    Log.e("haha",channellist.get(position).getCh_name());
//                    presenter.getRadiomusicList(0,30,channellist.get(position).getCh_name());
//                }
//            });

    }

    @Override
    public void iteMnusic(Song song) {
        musiclist.add(song);
        if(musiclist.size()==1){
            ((MainActivity)getActivity()).playMusic(musiclist,0);
        }else{
            ((MainActivity)getActivity()).setList(musiclist);
        }
    }


    @Override
    public void err(String err) {
        if(ProgressbarLay!=null){
            ProgressbarLay.setVisibility(View.GONE);
        }
        ToastUtil.showtoast(getContext(), err);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_loadMusic_back)
    public void onViewClicked() {
        ((MainActivity) getActivity()).onback();
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        setVisibleAndGone();
    }

    private void setVisibleAndGone() {
        tab2.getLocationOnScreen(mTabMiddleLocation);
        tab.getLocationOnScreen(mTabTopLocation);
        if (mTabMiddleLocation[1] <= mTabTopLocation[1]) {
            tab.setVisibility(View.VISIBLE);
            tab2.setVisibility(View.INVISIBLE);
        } else {
            tab.setVisibility(View.INVISIBLE);
            tab2.setVisibility(View.VISIBLE);
        }
    }




    @Override
    public void OnitemClicklistener(int position, View view) {
        musiclist.clear();
        switch (view.getId()){
            case R.id.fragment_radio_tuijian_recyclerview:
                presenter.getRadiomusicList(0,30,tuijian.get(position).getCh_name());
                break;
            case R.id.fragment_radio_shiguang_recyclerview:
                presenter.getRadiomusicList(0,30,shiguang.get(position).getCh_name());
                break;
            case R.id.fragment_radio_fengge_recyclerview:
                presenter.getRadiomusicList(0,30,fengge.get(position).getCh_name());
                break;
            case R.id.fragment_radio_xinqing_recyclerview:
                presenter.getRadiomusicList(0,30,xinqing.get(position).getCh_name());
                break;
            case R.id.fragment_radio_yuzhong_recyclerview:
                presenter.getRadiomusicList(0,30,yuzhong.get(position).getCh_name());
                break;
        }
    }
}
