package com.sixcat.utils

import android.text.TextUtils
import android.util.Log

import com.sixcat.BuildConfig

import java.util.Locale

/**
 * Log 工具类
 * @author liguoying
 * @date 2017/12/4.
 */

object LogUtil {
    private val isDebug = BuildConfig.DEBUG

    private fun generateTag(): String {
        val caller = Throwable().stackTrace[2]
        var tag = "%s.%s(L:%d)"
        var callerClazzName = caller.className
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        tag = String.format(Locale.CHINA, tag, callerClazzName, caller.methodName,
                caller.lineNumber)
        val customTagPrefix = "h_log"
        tag = if (TextUtils.isEmpty(customTagPrefix)) tag else "$customTagPrefix:$tag"
        return tag
    }

    fun d(content: Any?) {
        if (!isDebug || content == null) {
            return
        }
        val tag = generateTag()
        Log.d(tag, content.toString())
    }

    fun d(content: Any?, tr: Throwable) {
        if (!isDebug || content == null) {
            return
        }
        val tag = generateTag()
        Log.d(tag, content.toString(), tr)
    }

    fun e(content: Any?) {
        if (!isDebug || content == null) {
            return
        }
        val tag = generateTag()
        Log.e(tag, content.toString())
    }

    fun e(content: Any?, tr: Throwable) {
        if (!isDebug || content == null) {
            return
        }
        val tag = generateTag()
        Log.e(tag, content.toString(), tr)
    }

    fun i(content: Any?) {
        if (!isDebug || content == null) {
            return
        }
        val tag = generateTag()
        Log.i(tag, content.toString())
    }

    fun i(content: Any?, tr: Throwable) {
        if (!isDebug || content == null) {
            return
        }
        val tag = generateTag()
        Log.i(tag, content.toString(), tr)
    }
}
