package com.jie.ddmusic2.view.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.inject.DaggerLocalMusicComponent;
//import com.jie.ddmusic2.inject.impl.LocalpstComponent;
import com.jie.ddmusic2.inject.impl.LocalpstMoudle;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.Musicbeen;
import com.jie.ddmusic2.presenter.impl.LocalmusicPresenter;
import com.jie.ddmusic2.presenter.iport.ILocalmusicPresenter;
import com.jie.ddmusic2.view.act.MainActivity;
import com.jie.ddmusic2.view.adapter.LocalMusicAdapter;
import com.jie.ddmusic2.view.adapter.OnItenclickListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jie on 2017/7/20.
 */

public class LocalMusicFragment extends BaseFragment implements ILocalmusicPresenter.View{

    @BindView(R.id.fragment_loadMusic_back)
    ImageView fragmentLoadMusicBack;
    @BindView(R.id.fragment_loadMusic_recyclerview)
    RecyclerView fragmentLoadMusicRecyclerview;
    Unbinder unbinder;
    @Inject
    LocalmusicPresenter presenter;

    LocalMusicAdapter localMusicAdapter;
    public static final int LINE_HEIGHT = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loadmusic, container,false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerLocalMusicComponent.builder().localpstMoudle(new LocalpstMoudle(this))
                .build()
                .inject(this);
        presenter.getList();
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
    public void setList(final ArrayList<Musicbeen> list) {
        final ArrayList<Music> musiclist=new ArrayList<>();
        musiclist.addAll(list);
        localMusicAdapter=new LocalMusicAdapter(getActivity(),list);
        fragmentLoadMusicRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentLoadMusicRecyclerview.setAdapter(localMusicAdapter);
        drawLine();
        localMusicAdapter.setClick(new OnItenclickListener() {
            @Override
            public void OnitemclickListener(int position) {
                ((MainActivity) getActivity()).playMusic(musiclist,position);
            }

            @Override
            public void OnitemLongclickListener(int position) {

            }

            @Override
            public void OnMenuclickListener(int position) {

            }
        });
    }
    private void drawLine() {
        fragmentLoadMusicRecyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                int left = parent.getPaddingLeft();
                int reght = parent.getMeasuredWidth() - parent.getPaddingRight();
                int childCount = parent.getChildCount();
                int top = 0;
                int bottom = 0;
                for (int i = 0; i < childCount; i++) {
                    View v = parent.getChildAt(i);
                    top = v.getBottom();
                    bottom = v.getBottom() +  LINE_HEIGHT;
                    c.drawRect(left, top, reght, bottom, new Paint());
                }
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.contains(0, 0, 0, LINE_HEIGHT);
            }
        });
    }
}
