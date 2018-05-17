package com.jie.ddmusic2.view.act;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jie.ddmusic2.LyricView;
import com.jie.ddmusic2.Manager.OnMusicChangeListener;
import com.jie.ddmusic2.Manager.PlayMessageManager;
import com.jie.ddmusic2.R;
import com.jie.ddmusic2.http.DownLoadUtil;
import com.jie.ddmusic2.http.download;
import com.jie.ddmusic2.javabeen.Music;
import com.jie.ddmusic2.javabeen.Musicbeen;
import com.jie.ddmusic2.javabeen.Song;
import com.jie.ddmusic2.util.Final;
import com.jie.ddmusic2.util.PreferenceUtil;
import com.jie.ddmusic2.util.ToastUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicPlayDetailsActivity extends BaseActiivity implements OnMusicChangeListener, LyricView.OnPlayerClickListener, SeekBar.OnSeekBarChangeListener {

    public static final String KEY = "MusicPlayDetailsActivity";
    public static final int SONG = 0;
    public static final int LOADMUSIC = 1;
    @BindView(R.id.activity_music_play_details_musicnamme)
    TextView Musicname;
    @BindView(R.id.activity_music_play_details_musicsinger)
    TextView Musicsinger;
    @BindView(R.id.activity_music_play_details_currentprogress)
    TextView Currentprogress;


    @BindView(R.id.activity_music_play_details_duration)
    TextView Duration;
    @BindView(R.id.activity_music_play_details_upmusic)
    ImageView Upmusic;
    @BindView(R.id.activity_music_play_details_playorpause)
    ImageView Playorpause;
    @BindView(R.id.activity_music_play_details_nextmusic)
    ImageView Nextmusic;
    @BindView(android.R.id.progress)
    SeekBar Seekbar;
    @BindView(R.id.activity_music_play_details)
    LinearLayout activityMusicPlayDetails;
    @BindView(R.id.play_details_back)
    ImageView back;
    @BindView(R.id.activity_music_play_details_lrc)
    LyricView activityMusicPlayDetailsLrc;

    boolean seekbarOK=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_details);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
        ButterKnife.bind(this);
        PlayMessageManager.getInstance().setOnMusicChangeListener(KEY, this);
        Seekbar.setOnSeekBarChangeListener(this);

        activityMusicPlayDetailsLrc.setOnPlayerClickListener(this);

        activityMusicPlayDetailsLrc.setLineSpace(PreferenceUtil.getInstance(MusicPlayDetailsActivity.this).getFloat(PreferenceUtil.KEY_TEXT_SIZE, 12.0f));
        activityMusicPlayDetailsLrc.setTextSize(PreferenceUtil.getInstance(MusicPlayDetailsActivity.this).getFloat(PreferenceUtil.KEY_TEXT_SIZE, 15.0f));
        activityMusicPlayDetailsLrc.setHighLightTextColor(PreferenceUtil.getInstance(MusicPlayDetailsActivity.this).getInt(PreferenceUtil.KEY_HIGHLIGHT_COLOR, Color.parseColor("#4FC5C7")));
    }

    @Override
    public void playMessageChange(Music music) {
        Seekbar.setSecondaryProgress(0);
        if (music == null) {
            Musicname.setText("歌名");
            Musicsinger.setText("歌手");
            return;
        }
        if (music instanceof Musicbeen) {
            Musicbeen musicbeen = (Musicbeen) music;
            Musicname.setText(musicbeen.getMusic_name());
            Musicsinger.setText(musicbeen.getMusic_artist());
            setLrc(null,musicbeen.getMusic_name(),"",LOADMUSIC,musicbeen.getMusic_id());
        } else if (music instanceof Song) {
            Song song = (Song) music;
            Musicname.setText(song.getSonginfo().getTitle());
            Musicsinger.setText(song.getSonginfo().getAuthor());
            setLrc(song.getSonginfo().getLrclink(),song.getSonginfo().getTitle(),song.getSonginfo().getAuthor(),SONG,song.getSonginfo().getSong_id());
        }
    }

    @Override
    public void palyStatusChange(boolean flag) {
        if (flag) {
            Playorpause.setImageResource(R.drawable.pause);
        } else {
            Playorpause.setImageResource(R.drawable.play);
        }
    }

    @Override
    public void playBuffer(int schedule) {
        Seekbar.setSecondaryProgress(schedule);
    }

    @Override
    public void playProgress(int progress) {
        if(seekbarOK==true){
            Seekbar.setProgress(progress);
            activityMusicPlayDetailsLrc.setCurrentTimeMillis(progress);
            Currentprogress.setText(toText(progress/1000));
        }

    }

    @Override
    public void playDuration(int duraction) {
        Seekbar.setMax(duraction);
        Duration.setText(toText(duraction/1000));
    }

    private String toText(int number){
        String time;
        if(number>59){
            int i = number / 60;
            int i1 = number % 60;
            if(i1<10){
                time=i+":0"+i1;
            }else{
                time=i+":"+i1;
            }
        }else{
            if(number<10){
                time="0:0"+number;
            }else{
                time="0:"+number;
            }
        }
        return time;
    }


    @OnClick({R.id.activity_music_play_details_upmusic, R.id.activity_music_play_details_playorpause, R.id.activity_music_play_details_nextmusic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_music_play_details_upmusic:
                service.upmusic();
                break;
            case R.id.activity_music_play_details_playorpause:
                service.playorpause();
                break;
            case R.id.activity_music_play_details_nextmusic:
                service.next();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayMessageManager.getInstance().removeListener(KEY);
    }

    @TargetApi(19)
    private void setTranslucentStatus() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        final int status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        params.flags |= status;
        window.setAttributes(params);
    }

    @OnClick(R.id.play_details_back)
    public void onViewClicked() {
        onBackPressed();
    }

    private void setLrc(String url,String sonname,String singer,int i,String songid){
        ToastUtil.showtoast(this,songid);
        final File file=new File(Final.lyricPath+"/"+sonname+"-"+singer+".lrc");
        if(file.exists()&&file.length()>0){
            activityMusicPlayDetailsLrc.setLyricFile(file,"utf-8");
            activityMusicPlayDetailsLrc.setPlayable(true);
        }else if(i== SONG){
            activityMusicPlayDetailsLrc.reset("正在加载");
            activityMusicPlayDetailsLrc.setPlayable(false);
            DownLoadUtil.downloadLrc(url,sonname,singer);
            DownLoadUtil.regester(new download() {
                @Override
                public void success() {
                    activityMusicPlayDetailsLrc.setLyricFile(file,"utf-8");
                    activityMusicPlayDetailsLrc.setPlayable(true);
                    DownLoadUtil.unregester();
                }

                @Override
                public void err(String err) {
                    activityMusicPlayDetailsLrc.reset("加载失败");
                }
            });
        }else if(i== LOADMUSIC){
            activityMusicPlayDetailsLrc.reset("正在加载");
            activityMusicPlayDetailsLrc.setPlayable(false);
            DownLoadUtil.downloadLrc(file, new download() {
                @Override
                public void success() {
                    activityMusicPlayDetailsLrc.setLyricFile(file,"utf-8");
                    activityMusicPlayDetailsLrc.setPlayable(true);
                }

                @Override
                public void err(String err) {
                    activityMusicPlayDetailsLrc.reset("加载失败");
                }
            }
            ,songid);
        }

    }

    @Override
    public void onPlayerClicked(long progress, String content) {
        activityMusicPlayDetailsLrc.setCurrentTimeMillis(progress);
        service.seekto((int) progress);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            Seekbar.setProgress(progress);
            activityMusicPlayDetailsLrc.setCurrentTimeMillis(progress);
            Currentprogress.setText(toText(progress/1000));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekbarOK=false;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        service.seekto(seekBar.getProgress());
        seekbarOK=true;
    }
}
