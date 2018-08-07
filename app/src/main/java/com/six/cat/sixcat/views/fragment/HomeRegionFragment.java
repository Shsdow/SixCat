package com.six.cat.sixcat.views.fragment;

import android.os.Bundle;
import android.util.Log;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.views.base.BaseRxLazyFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeRegionFragment extends BaseRxLazyFragment {
    private static HomeRegionFragment instance;
    private static final String TAG = "HomeRegionFragment";

    public static HomeRegionFragment newInstance() {
        if (instance == null) {
            instance = new HomeRegionFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_video;
    }

    @Override
    public void finishCreateView(Bundle state) {
        Log.e(TAG, "finishCreateView: " + " " + TAG);
    }


    @Override
    public void onShowNetError() {

    }

    @Override
    public void mSetPresenter(Object presenter) {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }


}