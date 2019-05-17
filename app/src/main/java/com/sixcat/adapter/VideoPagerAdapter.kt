package com.sixcat.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.sixcat.base.BaseRxLazyFragment
import com.sixcat.model.bean.VideoChannelBean
import com.sixcat.views.fragment.VideoDetailFragment

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class VideoPagerAdapter(fragmentManager: FragmentManager, var videoChannelBean: VideoChannelBean?) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): BaseRxLazyFragment = VideoDetailFragment.newInstance("clientvideo_" + videoChannelBean!!.types!![position].id)

    override fun getPageTitle(position: Int): CharSequence = videoChannelBean!!.types!![position].name!!

    override fun getCount(): Int = videoChannelBean?.types?.size ?: 0

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
}