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
import com.jie.ddmusic2.javabeen.RadioList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jie on 2017/7/24.
 */

public class RadioListAdapter extends RecyclerView.Adapter<RadioListAdapter.MyViewHouder> {
    private List<RadioList.ResultBean.ChannellistBean> list;
    private Context context;
    private OnRadioItemclickListener click;
    private View view;

    public void setOnClickListener(OnRadioItemclickListener click) {
        this.click = click;
    }

    public RadioListAdapter(List<RadioList.ResultBean.ChannellistBean> list, Context context,RecyclerView view) {
        this.list = list;
        this.context = context;
        this.view=view;
    }

    @Override
    public MyViewHouder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHouder(LayoutInflater.from(context).inflate(R.layout.item_radio_musiclist, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHouder holder, final int position) {
        Glide.with(context).load(list.get(position).getThumb()).into(holder.itemRadioImage);
        holder.itemRadioName.setText(list.get(position).getName());
        holder.itemRadioLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.OnitemClicklistener(position,view);
            }
        });
    }

    class MyViewHouder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_radio_image)
        ImageView itemRadioImage;
        @BindView(R.id.item_radio_name)
        TextView itemRadioName;
        @BindView(R.id.item_radio_lay)
        LinearLayout itemRadioLay;

        public MyViewHouder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
