package com.six.cat.sixcat.fragment;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;

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
}
