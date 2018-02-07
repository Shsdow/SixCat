package com.six.cat.sixcat.module.video

import android.os.Bundle

import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.utils.LogUtil

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeVideoFragment : BaseRxLazyFragment<IVideoInterfaceManager.IVideoPresenter>(), IVideoInterfaceManager.IVideoView {

    companion object {
        fun newInstance(): HomeVideoFragment {
            val args = Bundle()
            val frgment = HomeVideoFragment()
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
        if (!isPrepared || !isVisible) {
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
            this.presenter = ViewPresenter(this)
        }
    }

    override fun loadData() {
        presenter?.doLoadData()
    }

    override fun doSetVideoData(videobeanList: List<VideoChannelBean>?) {
        LogUtil.e(videobeanList?.size)
    }

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onShowNetError() {

    }


    override fun onShowNoMore() {

    }






}