package com.sixcat.module.live

import com.sixcat.base.refactor.IBasePresenter
import com.sixcat.base.refactor.IBaseView
import com.sixcat.model.bean.LiveBean


/**
 * @author liguoying
 * *
 * @date 2018/1/9.
 */

interface LiveContrace {
    interface View : IBaseView {
        /**
         * 设置适配器数据
         */
        fun setLiveData(list: List<LiveBean.SubjectsBean>?, startCount: Int, totalCount: Int)

        fun showError(errorMsg: String, errorCode: Int)

    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 刷新数据
         */
        fun loadData(city: String = "北京", start: Int, count: Int)

        /**
         * 再起请求数据
         */
        fun loadMoreData()

    }
}
