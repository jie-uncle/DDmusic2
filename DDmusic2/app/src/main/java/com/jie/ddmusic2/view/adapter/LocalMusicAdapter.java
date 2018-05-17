package com.jie.ddmusic2.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jie.ddmusic2.R;
import com.jie.ddmusic2.javabeen.Musicbeen;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jie on 2017/7/6.
 */

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Musicbeen> list;
    private OnItenclickListener click;

    public void setClick(OnItenclickListener click) {
        this.click = click;
    }


    public LocalMusicAdapter(Context context, ArrayList<Musicbeen> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_locamusic_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemLocamusicTital.setText(list.get(position).getMusic_name());
        holder.itemLocamusicSinger.setText(list.get(position).getMusic_artist());
        holder.itemLocamusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.OnitemclickListener(position);
            }
        });
        holder.itemLocamusic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                click.OnitemLongclickListener(position);
                return true;
            }
        });
        holder.itemLocamusicMoremenu.setOnClickListener(new View.OnClickListener() {
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_locamusic)
        LinearLayout itemLocamusic;
        @BindView(R.id.item_locamusic_tital)
        TextView itemLocamusicTital;
        @BindView(R.id.item_locamusic_singer)
        TextView itemLocamusicSinger;
        @BindView(R.id.item_locamusic_moremenu)
        ImageView itemLocamusicMoremenu;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
