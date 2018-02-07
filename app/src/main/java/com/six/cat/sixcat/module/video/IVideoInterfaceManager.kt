package com.six.cat.sixcat.module.video

import com.six.cat.sixcat.bean.VideoChannelBean
import com.six.cat.sixcat.bean.VideoDetailBean
import com.six.cat.sixcat.module.base.IBasePresenter
import com.six.cat.sixcat.module.base.IBaseView

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