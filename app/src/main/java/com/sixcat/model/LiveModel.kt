package com.sixcat.model

import com.sixcat.RetrofitManager
import com.sixcat.api.SchedulerUtils
import com.sixcat.model.bean.LiveBean
import io.reactivex.Observable

/**
 * Author：Administrator
 * Data: 2018/9/6 0006 16:38
 */

class LiveModel {
    fun getLiveData(city: String, start: Int, count: Int): Observable<LiveBean> {
        return RetrofitManager.service.getLiveContent(city, start, count)
                .compose(SchedulerUtils.ioToMain())
    }
}