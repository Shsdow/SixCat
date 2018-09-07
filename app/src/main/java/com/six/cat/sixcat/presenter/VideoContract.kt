package com.six.cat.sixcat.presenter

import com.six.cat.sixcat.base.refactor.IBasePresenter
import com.six.cat.sixcat.base.refactor.IBaseView
import com.six.cat.sixcat.model.bean.VideoChannelBean
import com.six.cat.sixcat.model.bean.VideoDetailBean

/**
 * @author liguoying
 * @date 2018/2/6.
 */
interface VideoContract {

    interface Presenter : IBasePresenter<View> {
        fun getVideoChannel(mPage: Int)
        fun getVideoDetails(page: Int, listType: String, typeId: String)
        fun doNotShowMore()
    }

    interface View : IBaseView {
        fun setVideoChanel(videobeanList: List<VideoChannelBean>?)
        fun loadVideoDetails(detailBean: List<VideoDetailBean>?)
        fun showError(errorMsg: String, errorCode: Int)
    }
}