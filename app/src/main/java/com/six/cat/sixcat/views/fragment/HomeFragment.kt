package com.six.cat.sixcat.views.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.six.cat.sixcat.R
import com.six.cat.sixcat.R.id.tabHome
import com.six.cat.sixcat.R.id.vpHome
import com.six.cat.sixcat.adapter.HomeFgAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.utils.SettingUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author：Administrator
 * Data: 2018/9/4  11:45
 */

open class HomeFragment : BaseRxLazyFragment() {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private val homeFgAdapter by lazy { HomeFgAdapter(childFragmentManager, activity) }

    override fun getLayoutResId() = R.layout.fragment_home

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