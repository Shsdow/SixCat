package com.six.cat.sixcat.presenter

import com.six.cat.sixcat.base.refactor.IBasePresenter
import com.six.cat.sixcat.base.refactor.IBaseView
import com.six.cat.sixcat.model.bean.MovieShowcaseBean

/**
 * @author liguoying
 * @date 2018/1/27.
 */
interface MovieDetailContract {
    interface View : IBaseView {
        fun setMovieDetail(movieShowcaseBean: MovieShowcaseBean)
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IBasePresenter<View> {
        fun doLoadData(movieId: String?)
    }
}