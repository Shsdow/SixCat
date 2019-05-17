package com.sixcat.views.fragment

import com.sixcat.R
import com.sixcat.base.BaseRxLazyFragment

/**
 * @author liguoying
 * @date 2017/12/11.
 */

class ThemeFragment : BaseRxLazyFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_home
    }


    override fun initView() {

    }

    override fun lazyLoad() {

    }

    companion object {

        fun newInstance(): ThemeFragment {
            return ThemeFragment()
        }
    }
}
