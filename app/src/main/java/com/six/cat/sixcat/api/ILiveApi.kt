package com.six.cat.sixcat.api

import com.six.cat.sixcat.bean.LiveBean
import com.six.cat.sixcat.bean.MovieShowcaseBean

import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author liguoying
 * @date 2018/1/12.
 */

interface ILiveApi {

    /**
     * @GET("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&client=somemessage&udid=dddddddddddddddddddddd ")
     * Observable<LiveBean> getLiveContent(
     * @Query("city") String city,
     * @Query("start") int start,
     * @Query("count") int count
     * );
    </LiveBean> */
    @GET("https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&client=somemessage&udid=dddddddddddddddddddddd ")
    fun getLiveContent(
            @Query("city") city: String,
            @Query("start") start: Int,
            @Query("count") count: Int
    ): Observable<LiveBean>

    @GET("http://api.douban.com/v2/movie/subject/{movieId}?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&client=something&udid=dddddddddddddddddddddd")
    fun getMovieShowcase(
            @Path("movieId") movieId: String?
    ): Observable<MovieShowcaseBean>
}
