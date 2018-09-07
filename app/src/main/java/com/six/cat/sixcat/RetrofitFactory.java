package com.six.cat.sixcat;

import android.support.annotation.NonNull;

import com.six.cat.sixcat.presenter.ApiContants;
import com.six.cat.sixcat.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public class RetrofitFactory {
    private static final Object Object = new Object();
    private volatile static Retrofit retrofit;
    /**
     * 缓存机制
     * 在响应请求之后在 data/data/<包名>/cache 下建立一个response 文件夹，保持缓存数据。
     * 这样我们就可以在请求的时候，如果判断到没有网络，自动读取缓存的数据。
     * 同样这也可以实现，在我们没有网络的情况下，重新打开App可以浏览的之前显示过的内容。
     * 也就是：判断网络，有网络，则从网络获取，并保存到缓存中，无网络，则从缓存中获取。
     * https://werb.github.io/2016/07/29/%E4%BD%BF%E7%94%A8Retrofit2+OkHttp3%E5%AE%9E%E7%8E%B0%E7%BC%93%E5%AD%98%E5%A4%84%E7%90%86/
     */
    private static final Interceptor cacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.INSTANCE.isNetworkConnected(SixCatApplication.Companion.getContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.INSTANCE.isNetworkConnected(SixCatApplication.Companion.getContext())) {
                // 有网络时 设置缓存为默认值
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时 设置超时为1周
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    @NonNull
    public static Retrofit getRetrofit() {
        synchronized (Object) {
            if (retrofit == null) {
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                // 指定缓存路径,缓存大小 50Mb
                Cache cache = new Cache(new File(SixCatApplication.Companion.getContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 50);

                // Cookie 持久化
//                ClearableCookieJar cookieJar =
//                        new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(InitApp.AppContext));

                OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                        .cookieJar(cookieJar)
                        .cache(cache)
                        .addInterceptor(cacheControlInterceptor)
//                        .addNetworkInterceptor(new StethoInterceptor())
//                        .addInterceptor(interceptor)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .sslSocketFactory(SSLSocketClient.INSTANCE.getSSLSocketFactory())//配置
                        .hostnameVerifier(SSLSocketClient.INSTANCE.getHostnameVerifier())//配置
                        .retryOnConnectionFailure(true);

                // Log 拦截器
                if (BuildConfig.DEBUG) {
//                    builder = SdkManager.initInterceptor(builder);
                }

                retrofit =  new Retrofit.Builder()
                        .baseUrl(ApiContants.INSTANCE.getHOST_API())
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}
