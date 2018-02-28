package com.six.cat.sixcat.module.video

import android.os.Bundle

import com.six.cat.sixcat.R
import com.six.cat.sixcat.adapter.VideoPagerAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.bean.VideoChannelBean
import com.six.cat.sixcat.bean.VideoDetailBean
import kotlinx.android.synthetic.main.fragment_home_video.*

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class VideoShowFragment : BaseRxLazyFragment<IVideoInterfaceManager.IVideoPresenter>(), IVideoInterfaceManager.IVideoView {

    private var mVideoPagerAdapter: VideoPagerAdapter? = null

    companion object {
        fun newInstance(): VideoShowFragment {
            val args = Bundle()
            val frgment = VideoShowFragment()
            frgment.arguments = args
            return frgment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_home_video

    override fun finishCreateView(state: Bundle?) {
        isPrepared = true
        lazyLoad()
    }

    override fun lazyLoad() {
        if (!isPrepared) {
            return
        }
        initView()
        isPrepared = false
    }

    override fun initView() {
        loadData()
    }

    override fun setPresenter(presenter: IVideoInterfaceManager.IVideoPresenter?) {
        if (presenter == null) {
            this.presenter = VideoPresenter(this)
        }
    }

    override fun loadData() {
        presenter?.getVideoChannel()
    }

    override fun dosetVideoChanel(videobeanList: List<VideoChannelBean>?) {
        mVideoPagerAdapter = VideoPagerAdapter(childFragmentManager, videobeanList?.get(0))
        viewPager.adapter = mVideoPagerAdapter
        viewPager.offscreenPageLimit = 1
        viewPager.setCurrentItem(0, false)
        tabLayout.setupWithViewPager(viewPager, true)
    }

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onShowNetError() {

    }


    override fun onShowNoMore() {

    }

    override fun loadMoreVideoDetails(detailBean: List<VideoDetailBean>?) {
    }

    override fun loadVideoDetails(detailBean: List<VideoDetailBean>?) {
    }

}