package com.six.cat.sixcat.views.fragment;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.views.base.BaseRxLazyFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class PictureFragment extends BaseRxLazyFragment {

    public static PictureFragment newInstance() {
        return new PictureFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {

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