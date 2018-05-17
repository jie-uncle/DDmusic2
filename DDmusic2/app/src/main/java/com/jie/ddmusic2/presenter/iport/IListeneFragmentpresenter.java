package com.jie.ddmusic2.presenter.iport;

/**
 * Created by jie on 2017/7/5.
 */

public interface IListeneFragmentpresenter {
    interface  view{
        void intent2new(int id);

    }

    interface presenter{
        void intent_to(int id);
    }
}
