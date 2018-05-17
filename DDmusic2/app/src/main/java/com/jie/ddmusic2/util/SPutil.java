package com.jie.ddmusic2.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jie on 2017/7/13.
 */

public class SPutil {
    public static final String SP_NAME = "Config";
    public static final String PLAY_ORDER = "Play_Order";
    public static final int SEQUENTIAL=1;
    public static final int RANDOM=2;
    public static final int SINGLE=3;
    static SharedPreferences sp;

    public static void setSp(Context context) {
        sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }

    public static void setOrder(int play_order){
        sp.edit().putInt(PLAY_ORDER,play_order).commit();
    }

    public static int getOrder(){
        return sp.getInt(PLAY_ORDER, SEQUENTIAL);
    }
}
