package com.six.cat.sixcat.fragment.homefgs;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;
import com.six.cat.sixcat.fragment.HomeFragment;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeRegionFragment extends BaseRxLazyFragment {
    private static HomeRegionFragment instance;

    public static HomeRegionFragment newInstance() {
        if (instance == null) {
            instance = new HomeRegionFragment();
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