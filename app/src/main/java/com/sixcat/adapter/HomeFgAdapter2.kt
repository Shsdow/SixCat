package com.sixcat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sixcat.views.fragment.HomeLiveFragment
import com.sixcat.views.fragment.HomeRecommendedFragment
import com.sixcat.views.fragment.HomeRegionFragment
import com.sixcat.views.fragment.VideoFragment

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