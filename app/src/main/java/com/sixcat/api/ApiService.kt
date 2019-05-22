package com.sixcat.api

import com.sixcat.model.bean.MovieBean
import com.sixcat.model.bean.MovieShowcaseBean
import com.sixcat.model.bean.VideoChannelBean
import com.sixcat.model.bean.VideoDetailBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author：Administrator
 * Data: 2018/9/6 0006 14:19
 */
interface ApiService {
    /**
     * 电影列表
     * @GET("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&client=somemessage&udid=dddddddddddddddddddddd ")
     * Observable<MovieBean> getLiveContent(
     * @Query("city") String city,
     * @Query("start") int start,
     * @Query("count") int count
     * );
    </MovieBean> */
    @GET("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&client=somemessage&udid=dddddddddddddddddddddd")
    fun getLiveContent(
            @Query("city") city: String,
            @Query("start") start: Int,
            @Query("count") count: Int
    ): Observable<MovieBean>


    /**
     * 电影详情
     */
    @GET("http://api.douban.com/v2/movie/subject/{movieId}?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&client=something&udid=dddddddddddddddddddddd")
    fun getMovieShowcase(
            @Path("movieId") movieId: String?
    ): Observable<MovieShowcaseBean>


    /**
     * 视频频道类别
     */
    @GET("http://api.iclient.ifeng.com/ifengvideoList")
    fun getVideoChannel(@Query("page") page: Int = 1): Observable<List<VideoChannelBean>>

    @GET("http://api.iclient.ifeng.com/ifengvideoList")
    fun getVideoDetail(@Query("page") page: Int,
                       @Query("listtype") listtype: String,
                       @Query("typeid") typeid: String): Observable<List<VideoDetailBean>>
}