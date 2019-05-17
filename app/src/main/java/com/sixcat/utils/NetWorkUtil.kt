package com.sixcat.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * 网络连接状态工具类
 * @author liguoying
 * @date 2017/12/4.
 */

object NetWorkUtil {
    /**
     * 判断是否有网络连接
     */
    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空
            return null != networkInfo && networkInfo.isAvailable
        }
        return false
    }

    /**
     * 判断WIFI网络是否可用
     */
    fun isWifiConnected(context: Context?): Boolean {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空 并且类型是否为WIFI
            if (null != networkInfo && networkInfo.type == ConnectivityManager.TYPE_WIFI)
                return networkInfo.isAvailable
        }
        return false
    }

    /**
     * 判断MOBILE网络是否可用
     */
    fun isMobileConnected(context: Context?): Boolean {
        if (context != null) {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (null != networkInfo && networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                return networkInfo.isAvailable
        }
        return false
    }
}
