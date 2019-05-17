package com.sixcat.presenter.contract

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.sixcat.base.refactor.BasePresenter
import com.sixcat.model.MovieDetailModel
import com.sixcat.presenter.MovieDetailContract

/**
 * @author liguoying
 * @date 2018/1/29.
 */
class MovieDetailPresenter : BasePresenter<MovieDetailContract.View>(), MovieDetailContract.Presenter {

    private val movieModel by lazy { MovieDetailModel() }
    override fun doLoadData(movieId: String?) {
        checkViewAttached()
        val disposable = movieModel.getMovieInfo(movieId)
                .subscribe({ movieShowcasebean ->
                    mRootView?.apply {
                        dismissLoading()
                        setMovieDetail(movieShowcasebean)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

}