package com.jie.ddmusic2.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.javabeen.Song;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jie on 2017/7/18.
 */

public class NetMusicListDetilAdapter extends RecyclerView.Adapter<NetMusicListDetilAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Song> list;
    private OnItenclickListener click;

    public void setClick(OnItenclickListener click) {
        this.click = click;
    }

    @Inject
    public NetMusicListDetilAdapter(Context context, ArrayList<Song> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_net_musiclist_details, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemNetMusicTital.setText(list.get(position).getSonginfo().getTitle());
        holder.itemNetMusicSinger.setText(list.get(position).getSonginfo().getAuthor());
        holder.itemNetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.OnitemclickListener(position);
            }
        });
        holder.itemNetMusic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                click.OnitemLongclickListener(position);
                return true;
            }
        });
        holder.itemNetMusicMoremenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.OnMenuclickListener(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_net_music_tital)
        TextView itemNetMusicTital;
        @BindView(R.id.item_net_music_singer)
        TextView itemNetMusicSinger;
        @BindView(R.id.item_net_music_moremenu)
        ImageView itemNetMusicMoremenu;
        @BindView(R.id.item_net_music)
        LinearLayout itemNetMusic;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
