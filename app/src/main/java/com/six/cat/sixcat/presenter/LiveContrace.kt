package com.six.cat.sixcat.module.live

import com.six.cat.sixcat.base.refactor.IBasePresenter
import com.six.cat.sixcat.base.refactor.IBaseView
import com.six.cat.sixcat.model.bean.LiveBean


/**
 * @author liguoying
 * *
 * @date 2018/1/9.
 */

interface LiveContrace {
    interface View : IBaseView {
        /**
         * 设置适配器
         */
        fun setLiveData(list: List<LiveBean.SubjectsBean>?, totalCount: Int)

        fun showError(errorMsg: String, errorCode: Int)

        fun haveNoMore()
    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 刷新数据
         */
        fun loadData(city: String, start: Int, count: Int)
        /**
         * 再起请求数据
         */
        fun loadMoreData()

    }
}
