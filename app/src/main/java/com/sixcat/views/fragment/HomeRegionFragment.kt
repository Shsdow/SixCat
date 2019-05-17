package com.sixcat.views.fragment

import com.sixcat.R
import com.sixcat.base.BaseRxLazyFragment

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeRegionFragment : BaseRxLazyFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home_video
    }


    override fun initView() {

    }

    override fun lazyLoad() {

    }

    companion object {
        fun newInstance(): PictureFragment {
            return PictureFragment()
        }
    }
}