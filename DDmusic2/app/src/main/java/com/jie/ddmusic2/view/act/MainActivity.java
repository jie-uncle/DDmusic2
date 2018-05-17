package com.jie.ddmusic2.view.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.jie.ddmusic2.Manager.OnMusicChangeListener;
import com.jie.ddmusic2.Manager.PlayMessageManager;
import com.jie.ddmusic2.R;
import com.jie.ddmusic2.inject.DaggerIMainActivityComponent;
import com.jie.ddmusic2.inject.impl.MainActivityMoudle;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.util.Final;
import com.jie.ddmusic2.view.fragment.BaseFragment;
import com.jie.ddmusic2.view.fragment.MainFragment;
import com.jie.ddmusic2.view.fragment.Main_bottomFragment;
import com.jie.ddmusic2.view.fragment.NetMusicListDetailsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActiivity implements OnMusicChangeListener {



    public static final String KEY = "MainActivity";
    @BindView(R.id.activity_main)
    DrawerLayout drawerlayout;

    @Inject
    MainFragment mainFragment;
    @Inject
    Main_bottomFragment main_bottomFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PlayMessageManager.getInstance().setOnMusicChangeListener(KEY,this);


        DaggerIMainActivityComponent.builder()
                .mainActivityMoudle(new MainActivityMoudle())
                .build()
                .inject(this);

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.mian_fragment_content, mainFragment);
        ft.add(R.id.main_fragment_bottom, main_bottomFragment);
        ft.commit();
    }

    public void addFragment(BaseFragment fragment, boolean flag, BaseFragment closefragment) {
        FragmentTransaction ft = fm.beginTransaction();

        if (flag) {
            ft.add(R.id.mian_fragment_content, fragment);
            ft.hide(closefragment);
            ft.addToBackStack(null);
        }else{
            ft.replace(R.id.mian_fragment_content, fragment);
        }
        ft.commit();
    }

    public void intent2details(int type,BaseFragment baseFragment){
        NetMusicListDetailsFragment fragment=new NetMusicListDetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(Final.API_MUSIC_TYPE,type);
        fragment.setArguments(bundle);
        addFragment(fragment,true,baseFragment);
    }

    public void playMusic( ArrayList<Music> list,int position){
        service.playMusic(list,position);
    }

    public void play_or_pauseMusic(){
        service.playorpause();
    }

    public void nextMusic(){

        service.next();
    }

    public void seekto(int progress){
        service.seekto(progress);
    }

    public void setList(ArrayList<Music> list){
        service.setList(list);
    }

    @Override
    public void playMessageChange(Music music) {
        main_bottomFragment.setMessage(music);

    }

    @Override
    public void palyStatusChange(boolean flag) {
        main_bottomFragment.setPlayStatus(flag);
    }

    @Override
    public void playBuffer(int schedule) {
        main_bottomFragment.setbufferStatus(schedule );
    }

    @Override
    public void playProgress( int progress) {
        main_bottomFragment.setSeekBar_Progress(progress);
    }

    @Override
    public void playDuration(int duraction) {
        main_bottomFragment.setSeekbarDuraction(duraction);
    }

    @Override
    public void onBackPressed() {
        if(drawerlayout.isDrawerOpen(Gravity.LEFT)){
            drawerlayout.closeDrawer(Gravity.LEFT,true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        PlayMessageManager.getInstance().removeListener(KEY);
        super.onDestroy();
    }

    public void onback(){
        onBackPressed();
    }

    public void open_or_close(boolean flag){
        if(flag){
            drawerlayout.openDrawer(Gravity.LEFT,true);
        }else{
            drawerlayout.closeDrawer(Gravity.LEFT,true);
        }
    }

    public void intent2MusicPlay(){
        Intent i=new Intent(this,MusicPlayDetailsActivity.class);
        startActivity(i);
    }

    public void setMusicProgress(int progress) {
        HashMap<String, OnMusicChangeListener> map = PlayMessageManager.getInstance().getOnMusicChangeListener();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            OnMusicChangeListener listener = map.get(iterator.next());
            listener.playProgress(progress);
        }
    }
}
