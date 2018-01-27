package com.six.cat.sixcat.module.movieshowcase

import com.six.cat.sixcat.module.base.IBasePresenter
import com.six.cat.sixcat.module.base.IBaseView

/**
 * @author liguoying
 * @date 2018/1/27.
 */
interface IMovieShowcaseManager {
    interface IMoviewShowcaseView : IBaseView<IMoviewShowcasePresenter> {
        fun loadData()
    }

    interface IMoviewShowcasePresenter : IBasePresenter {
        fun doLoadData()
    }
}