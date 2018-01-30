package com.six.cat.sixcat.module.live

import com.six.cat.sixcat.bean.LiveBean
import com.six.cat.sixcat.module.base.IBasePresenter
import com.six.cat.sixcat.module.base.IBaseView

/**
 * @author liguoying
 * *
 * @date 2018/1/9.
 */

interface ILiveInterface {
    interface ILiveView : IBaseView<ILivePresenter> {
        fun loadData()
        /**
         * 设置适配器
         */
        abstract fun onSetAdapter(list: List<*>)
    }

    interface ILivePresenter : IBasePresenter {
        /**
         * 请求数据
         */
        fun doLoadData()

        /**
         * 再起请求数据
         */
        fun doLoadMoreData()

        /**
         * 设置适配器
         */
        fun doSetAdapter(mList: List<LiveBean.SubjectsBean>)

        fun doShowNoMore()
    }
}
