package com.jie.ddmusic2.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.view.fragment.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by jie on 2017/7/4.
 */

public class MainViewpagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> list;
    private Context context;

    public MainViewpagerAdapter(FragmentManager fm, ArrayList<BaseFragment> list, Context context) {
        super(fm);
        this.list=list;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getStringArray(R.array.fragment_list)[position];
    }
}
