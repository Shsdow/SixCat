package com.six.cat.sixcat.adapter
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.bean.VideoChannelBean
import com.six.cat.sixcat.module.video.VideoDetailFragment

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class VideoPagerAdapter(fragmentManager: FragmentManager, private val videoChannelBean: VideoChannelBean?):FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): BaseRxLazyFragment<*>
            = VideoDetailFragment.newInstance("clientvideo_" + videoChannelBean!!.types!![position].id)

    override fun getPageTitle(position: Int): CharSequence = videoChannelBean!!.types!![position].name!!

    override fun getCount(): Int = videoChannelBean?.types?.size ?: 0

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
}