package com.six.cat.sixcat.views.fragment

import android.os.Bundle

import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.trello.rxlifecycle2.LifecycleTransformer

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
