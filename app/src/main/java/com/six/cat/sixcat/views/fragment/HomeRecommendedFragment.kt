package com.six.cat.sixcat.views.fragment

import android.os.Bundle
import android.util.Log

import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeRecommendedFragment : BaseRxLazyFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home_video
    }


    override fun initView() {

    }

    override fun lazyLoad() {

    }

    companion object {

        fun newInstance(): HomeRecommendedFragment {
            return HomeRecommendedFragment()
        }
    }
}