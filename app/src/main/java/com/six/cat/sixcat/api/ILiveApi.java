package com.six.cat.sixcat.api;

import com.six.cat.sixcat.bean.LiveBean;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public interface ILiveApi {

    @GET("/in_theaters/?apikey=0b2bdeda43b5688921839c8ecb20399b&client=somemessage&udid=dddddddddddddddddddddd ")
    Observable<LiveBean> getLiveContent(
            @Query("city") String city,
            @Query("start") int start,
            @Query("count") int count
    );
}
