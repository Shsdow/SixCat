package com.sixcat.views.fragment

import android.os.Bundle

import com.sixcat.R
import com.sixcat.adapter.VideoPagerAdapter
import com.sixcat.base.BaseRxLazyFragment
import com.sixcat.model.bean.VideoChannelBean
import com.sixcat.model.bean.VideoDetailBean
import com.sixcat.presenter.VideoContract
import com.sixcat.presenter.contract.VideoPresenter
import kotlinx.android.synthetic.main.fragment_home_video.*

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class VideoShowFragment : BaseRxLazyFragment(), VideoContract.View {


//    private var mVideoPagerAdapter: VideoPagerAdapter? = null

    private val mVideoPagerAdapter by lazy { VideoPagerAdapter(childFragmentManager, VideoChannelBean()) }

    private val presenter by lazy { VideoPresenter() }

    init {
        presenter.attachView(this)
    }

    companion object {
        fun newInstance(): VideoShowFragment {
            val args = Bundle()
            val frgment = VideoShowFragment()
            frgment.arguments = args
            return frgment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_home_video

//    override fun finishCreateView(state: Bundle?) {
//        isPrepared = true
//        lazyLoad()
//    }
//
//    override fun lazyLoad() {
//        if (!isPrepared) {
//            return
//        }
//        initView()
//        isPrepared = false
//    }

    override fun initView() {
        loadData()
    }

    fun loadData() {
        presenter!!.getVideoChannel(1)
    }

    override fun lazyLoad() {
    }

    override fun loadVideoDetails(detailBean: List<VideoDetailBean>?) {
    }

    override fun setVideoChanel(videobeanList: List<VideoChannelBean>?) {
        viewPager.adapter = mVideoPagerAdapter
        viewPager.offscreenPageLimit = 1
        viewPager.setCurrentItem(0, false)
        tabLayout.setupWithViewPager(viewPager, true)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}