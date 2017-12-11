package com.six.cat.sixcat.fragment;

import android.os.Bundle;

import com.six.cat.sixcat.R;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class HomeFragment extends RxLazyFragment {
    private static HomeFragment instance;

    public static HomeFragment newInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
