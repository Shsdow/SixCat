package com.sixcat.views.fragment

import com.sixcat.R
import com.sixcat.base.BaseRxLazyFragment

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeRecommendedFragment : BaseRxLazyFragment() {

    override fun getLayoutResId() = R.layout.fragment_home_video


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