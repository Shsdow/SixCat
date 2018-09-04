package com.six.cat.sixcat.views.fragment;

import android.os.Bundle;
import android.util.Log;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeRecommendedFragment extends BaseRxLazyFragment {

    private static HomeRecommendedFragment instance;
    private static final String TAG = "HomeRecommendedFragment";

    public static HomeRecommendedFragment newInstance() {
        if (instance == null) {
            instance = new HomeRecommendedFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_video;
    }

    @Override
    public void finishCreateView(Bundle state) {
        Log.e(TAG, "finishCreateView: " + TAG);
    }


    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenterView(Object presenter) {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }


}