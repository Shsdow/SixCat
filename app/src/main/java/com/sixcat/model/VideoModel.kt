package com.sixcat.model

import com.sixcat.RetrofitManager
import com.sixcat.api.SchedulerUtils
import com.sixcat.model.bean.VideoChannelBean
import com.sixcat.model.bean.VideoDetailBean
import io.reactivex.Observable

/**
 * Author：Administrator
 * Data: 2018/9/7 0007 15:41
 */
class VideoModel {

    fun getVideoChannel(mPage: Int): Observable<List<VideoChannelBean>> {
        return RetrofitManager.service.getVideoChannel(mPage)
                .compose(SchedulerUtils.ioToMain())
    }

    fun getVideoDetails(page: Int, listType: String, typeId: String): Observable<List<VideoDetailBean>> {
        return RetrofitManager.service.getVideoDetail(page, listType, typeId)
                .compose(SchedulerUtils.ioToMain())
    }
}