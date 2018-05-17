package com.jie.ddmusic2.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jie.ddmusic2.R;
import com.jie.ddmusic2.javabeen.NewMusicBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jie on 2017/7/24.
 */

public class NetMusicListAdapter extends RecyclerView.Adapter<NetMusicListAdapter.ViewHouder> {
    private Context context;
    private List<NewMusicBean> list;
    OnItenclickListener click;

    public void setOnClickListener(OnItenclickListener click) {
        this.click = click;
    }

    public NetMusicListAdapter(Context context, List<NewMusicBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHouder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHouder(LayoutInflater.from(context).inflate(R.layout.item_net_musiclist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHouder holder, final int position) {
        NewMusicBean newMusicBean = list.get(position);

        if (newMusicBean == null) {
            return;
        }

        Glide.with(context).load(newMusicBean.getBillboard().getPic_s260()).into(holder.remoteKtvmusicImage);
        holder.remoteKtvmusicItem1.setText(newMusicBean.getSong_list().get(0).getTitle() + "-" + newMusicBean.getSong_list().get(0).getAuthor());
        holder.remoteKtvmusicItem2.setText(newMusicBean.getSong_list().get(1).getTitle() + "-" + newMusicBean.getSong_list().get(1).getAuthor());
        holder.remoteKtvmusicItem3.setText(newMusicBean.getSong_list().get(2).getTitle() + "-" + newMusicBean.getSong_list().get(2).getAuthor());
        holder.itemMusicListLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.OnitemclickListener(position);
            }
        });
    }




    class ViewHouder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_musicList_lay)
        LinearLayout itemMusicListLay;
        @BindView(R.id.remote_ktvmusic_image)
        ImageView remoteKtvmusicImage;
        @BindView(R.id.remote_ktvmusic_item1)
        TextView remoteKtvmusicItem1;
        @BindView(R.id.remote_ktvmusic_item2)
        TextView remoteKtvmusicItem2;
        @BindView(R.id.remote_ktvmusic_item3)
        TextView remoteKtvmusicItem3;

        public ViewHouder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
