package com.six.cat.sixcat.fragment.homefgs;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeRecommendedFragment extends BaseRxLazyFragment {

    private static HomeRecommendedFragment instance;

    public static HomeRecommendedFragment newInstance() {
        if (instance == null) {
            instance = new HomeRecommendedFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_ex;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}