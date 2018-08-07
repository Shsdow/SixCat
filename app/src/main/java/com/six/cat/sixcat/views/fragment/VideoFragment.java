package com.six.cat.sixcat.views.fragment;

import android.os.Bundle;
import android.util.Log;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.views.base.BaseRxLazyFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class VideoFragment extends BaseRxLazyFragment {
    private static final String TAG = "VideoFragment";

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {
        Log.e(TAG, "finishCreateView: " + " " + TAG);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

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