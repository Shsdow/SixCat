package com.six.cat.sixcat.module.live

import com.six.cat.sixcat.base.IBasePresenter
import com.six.cat.sixcat.base.IBaseView
import com.six.cat.sixcat.model.LiveBean

/**
 * @author liguoying
 * *
 * @date 2018/1/9.
 */

interface ILiveInterface {
    interface ILiveView : IBaseView<ILivePresenter> {

        /**
         * 设置适配器
         */
        fun onLoadDataSuccess(list: List<*>, totalCount: Int)

        fun haveNoMore()
    }

    interface ILivePresenter : IBasePresenter {

        /**
         * 刷新数据
         */
        fun doRefresh()
        /**
         * 再起请求数据
         */
        fun doLoadMoreData()

//        /**
//         * 设置适配器
//         */
//        fun doSetAdapter(mList: List<LiveBean.SubjectsBean>)
//
//        fun doShowNoMore()
    }
}
