package com.six.cat.sixcat.presenter

import com.six.cat.sixcat.model.MovieShowcaseBean

/**
 * @author liguoying
 * @date 2018/1/27.
 */
interface IMovieShowcaseManager {
    interface IMoviewShowcaseView : IBaseView<IMoviewShowcasePresenter> {
        fun loadData()
        fun doSetData(movieShowcaseBean: MovieShowcaseBean)
    }

    interface IMoviewShowcasePresenter : IBasePresenter {
        fun doLoadData(movieId: String?)

        fun doSetData(movieShowcaseBean: MovieShowcaseBean)

        fun doNotShowMore()
    }
}