package com.six.cat.sixcat.model

import com.six.cat.sixcat.RetrofitManager
import com.six.cat.sixcat.api.SchedulerUtils
import com.six.cat.sixcat.model.bean.MovieShowcaseBean
import io.reactivex.Observable

/**
 * Author：Administrator
 * Data: 2018/9/6 0006 14:14
 */
class MovieDetailModel {

    fun getMovieInfo(movieId: String?): Observable<MovieShowcaseBean> {
        return RetrofitManager.service.getMovieShowcase(movieId)
                .compose(SchedulerUtils.ioToMain())
    }
}