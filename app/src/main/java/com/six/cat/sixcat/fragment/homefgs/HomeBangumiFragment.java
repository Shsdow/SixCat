package com.six.cat.sixcat.fragment.homefgs;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeBangumiFragment extends BaseRxLazyFragment {
    private static HomeBangumiFragment instance;

    public static HomeBangumiFragment newInstance() {
        if (instance == null) {
            instance = new HomeBangumiFragment();
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