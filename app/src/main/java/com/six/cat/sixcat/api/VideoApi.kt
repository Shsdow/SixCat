package com.six.cat.sixcat.api

import com.six.cat.sixcat.module.video.VideoChannelBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author liguoying
 * @date 2018/2/7.
 */
interface VideoApi {

    @GET("http://api.iclient.ifeng.com/ifengvideoList")
    fun getVideoChannel(@Query("page") page: Int = 1): Observable<List<VideoChannelBean>>
}