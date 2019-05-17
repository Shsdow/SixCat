package com.six.cat.sixcat.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.six.cat.sixcat.views.fragment.HomeLiveFragment
import com.six.cat.sixcat.views.fragment.HomeRecommendedFragment
import com.six.cat.sixcat.views.fragment.HomeRegionFragment
import com.six.cat.sixcat.views.fragment.VideoFragment

/**
 * Author：Administrator
 * Data: 2018/9/7 0007 10:39
 */
class HomeFgAdapter2(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val mFragmentList: List<Fragment> by lazy {
        listOf(
                HomeLiveFragment.newInstance(),
                HomeRecommendedFragment.newInstance(),
                VideoFragment.newInstance(),
                HomeRegionFragment.newInstance()
        )
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> mFragmentList[0]
            1 -> mFragmentList[1]
            2 -> mFragmentList[2]
            3 -> mFragmentList[3]
            else -> mFragmentList[0]

        }
    }

    override fun getCount() = mFragmentList.size

}