package com.six.cat.sixcat.fragment;

import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class VideoFragment extends BaseRxLazyFragment {

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}