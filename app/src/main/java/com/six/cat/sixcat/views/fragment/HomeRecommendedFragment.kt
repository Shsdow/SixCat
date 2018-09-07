package com.six.cat.sixcat.views.fragment

import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseRxLazyFragment

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