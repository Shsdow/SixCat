package com.sixcat.views.fragment

import com.google.android.material.tabs.TabLayout
import com.sixcat.R
import com.sixcat.adapter.HomeFgAdapter
import com.sixcat.base.BaseRxLazyFragment
import com.sixcat.utils.SettingUtil
import kotlinx.android.synthetic.main.fragment_film_list.*

/**
 * Author：Administrator
 * Data: 2018/9/4  11:45
 */

open class FileListFragment : BaseRxLazyFragment() {

    companion object {
        fun newInstance(): FileListFragment = FileListFragment()
    }

    private val homeFgAdapter by lazy { HomeFgAdapter(childFragmentManager, activity) }

    override fun getLayoutResId() = R.layout.fragment_film_list

    override fun initView() {
        initViewPage()
    }

    fun initViewPage() {
        vpHome.adapter = homeFgAdapter
        vpHome.offscreenPageLimit = 3
        tabHome.setupWithViewPager(vpHome)
        tabHome.tabMode = TabLayout.MODE_SCROLLABLE
        tabHome.setBackgroundColor(SettingUtil.instance.color)
    }

    override fun lazyLoad() {
    }

}