package com.sixcat.presenter

import com.sixcat.base.refactor.IBasePresenter
import com.sixcat.base.refactor.IBaseView
import com.sixcat.model.bean.MovieShowcaseBean

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