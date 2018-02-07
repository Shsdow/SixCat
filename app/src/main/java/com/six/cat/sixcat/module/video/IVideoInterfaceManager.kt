package com.six.cat.sixcat.module.video

import com.six.cat.sixcat.module.base.IBasePresenter
import com.six.cat.sixcat.module.base.IBaseView

/**
 * @author liguoying
 * @date 2018/2/6.
 */
interface IVideoInterfaceManager {

    interface IVideoPresenter : IBasePresenter {
        fun doLoadData()
        fun doNotShowMore()
    }

    interface IVideoView : IBaseView<IVideoPresenter> {
        fun loadData()
        fun doSetVideoData(videobeanList: List<VideoChannelBean>?)
    }
}