package com.sixcat.views.fragment

import com.sixcat.R
import com.sixcat.base.BaseRxLazyFragment

/**
 * @author liguoying
 * @date 2017/12/11.
 */

class VideoFragment : BaseRxLazyFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_todo_list
    }


    override fun initView() {

    }

    override fun lazyLoad() {

    }

    companion object {
        private val TAG = "VideoFragment"

        fun newInstance(): VideoFragment {
            return VideoFragment()
        }
    }
}