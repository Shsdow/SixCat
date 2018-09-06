package com.six.cat.sixcat.presenter

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.six.cat.sixcat.base.refactor.BasePresenter
import com.six.cat.sixcat.model.MovieDetailModel

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