package com.six.cat.sixcat

import com.six.cat.sixcat.api.ApiService
import com.six.cat.sixcat.presenter.ApiContants
import com.six.cat.sixcat.utils.NetWorkUtil
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Author：Administrator
 * Data: 2018/9/6 0006 14:26
 */
object RetrofitManager {


    private var retrofit: Retrofit? = null
    private var client: OkHttpClient? = null
    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }

    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request: Request
            val modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("phoneSystem", "")
                    .addQueryParameter("phoneModel", "")
                    .build()
            request = originalRequest.newBuilder().url(modifiedUrl).build()
            chain.proceed(request)
        }
    }

    /**
     * 设置头
     */
    private fun addHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token", "")
                    .method(originalRequest.method(), originalRequest.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }


    private fun cacheControlInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetWorkUtil.isNetworkConnected(SixCatApplication.context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val originalResponse = chain.proceed(request)
            if (NetWorkUtil.isMobileConnected(SixCatApplication.context)) {
                val cacheControl = request.cacheControl().toString()
                originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build()
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                val maxStale = 60 * 60 * 24 * 28
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("nyn")
                        .build()
            }
            originalResponse
        }
    }

    fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    val cache = Cache(File(SixCatApplication.context.cacheDir, "sixcatCache"), 1024 * 1014 * 50)
                    //添加一个log拦截器,打印所有的log
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    client = OkHttpClient.Builder()
                            .cache(cache)
//                            .addInterceptor(cacheControlInterceptor())
                            .addInterceptor(httpLoggingInterceptor)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                            .retryOnConnectionFailure(true)
                            .build()

                    retrofit = Retrofit.Builder()
                            .baseUrl(ApiContants.HOST_API)
                            .client(client!!)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                }
            }
        }
        return retrofit
    }
}