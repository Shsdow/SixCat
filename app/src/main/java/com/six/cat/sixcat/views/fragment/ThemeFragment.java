package com.six.cat.sixcat.views.fragment;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.views.base.BaseRxLazyFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class ThemeFragment extends BaseRxLazyFragment {

    public static ThemeFragment newInstance() {
        return new ThemeFragment();
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
