package com.six.cat.sixcat.views.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.six.cat.sixcat.R
import com.six.cat.sixcat.adapter.HomeFgAdapter
import com.six.cat.sixcat.utils.SettingUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author：Administrator
 * Data: 2018/9/4 0004 11:45
 */

open class HomeFragment : Fragment() {

    private var homeFgAdapter: HomeFgAdapter? = null

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()

    }

    fun getLayoutResId() = R.layout.fragment_home

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), null, false)
    }


    fun finishCreateView(state: Bundle?) {
        initViewPage()
    }

    fun initViewPage() {
        homeFgAdapter = HomeFgAdapter(childFragmentManager, activity)
        vpHome.adapter = homeFgAdapter
        vpHome.offscreenPageLimit = 3
        tabHome.setupWithViewPager(vpHome)
        tabHome.tabMode = TabLayout.MODE_SCROLLABLE
        tabHome.setBackgroundColor(SettingUtil.getInstance().color)
    }
}