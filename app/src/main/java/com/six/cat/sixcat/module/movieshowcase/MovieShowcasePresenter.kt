package com.six.cat.sixcat.module.movieshowcase

import com.six.cat.sixcat.BuildConfig
import com.six.cat.sixcat.RetrofitFactory
import com.six.cat.sixcat.api.ILiveApi
import com.six.cat.sixcat.bean.MovieShowcaseBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author liguoying
 * @date 2018/1/29.
 */
class MovieShowcasePresenter(val mView: IMovieShowcaseManager.IMoviewShowcaseView) : IMovieShowcaseManager.IMoviewShowcasePresenter {


    override fun doRefresh() {
    }

    override fun doLoadData(movieId: String?) {
        RetrofitFactory.getRetrofit().create(ILiveApi::class.java).getMovieShowcase(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.bindToLife())
                .subscribe({ movieShowcasebean ->
                    if (movieShowcasebean != null) {
                        doSetData(movieShowcasebean)
                    } else {
                        doNotShowMore()
                    }
                }, { throwable ->
                    doShowNetError()
                    if (BuildConfig.DEBUG) {
                        throwable.printStackTrace()
                    }
                })
    }

    override fun doSetData(movieShowcaseBean: MovieShowcaseBean) {
        mView.onShowLoading()
        mView.doSetData(movieShowcaseBean)
    }

    override fun doShowNetError() {
        mView.onHideLoading()
        mView.onShowNoMore()
    }

    override fun doNotShowMore() {
        mView.onHideLoading()
        mView.onShowNoMore()
    }


}