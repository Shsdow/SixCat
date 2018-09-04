package com.six.cat.sixcat.presenter

import com.six.cat.sixcat.base.IBasePresenter
import com.six.cat.sixcat.base.IBaseView
import com.six.cat.sixcat.model.VideoChannelBean
import com.six.cat.sixcat.model.VideoDetailBean

/**
 * @author liguoying
 * @date 2018/2/6.
 */
interface IVideoInterfaceManager {

    interface IVideoPresenter : IBasePresenter {
        fun getVideoChannel()
        fun getVideoDetails(page: Int, listType: String, typeId: String)
        fun doNotShowMore()
    }

    interface IVideoView : IBaseView<IVideoPresenter> {
        fun loadData()
        fun dosetVideoChanel(videobeanList: List<VideoChannelBean>?)
        fun loadVideoDetails(detailBean: List<VideoDetailBean>?)
        fun loadMoreVideoDetails(detailBean: List<VideoDetailBean>?)
    }
}