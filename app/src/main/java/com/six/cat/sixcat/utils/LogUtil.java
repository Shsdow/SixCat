package com.six.cat.sixcat.utils;

import android.text.TextUtils;
import android.util.Log;

import com.six.cat.sixcat.BuildConfig;

import java.util.Locale;

/**
 * Log 工具类
 * @author liguoying
 * @date 2017/12/4.
 */

public class LogUtil {
    private static boolean isDebug = BuildConfig.DEBUG;

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(Locale.CHINA, tag, callerClazzName, caller.getMethodName(),
                caller.getLineNumber());
        String customTagPrefix = "h_log";
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(Object content) {
        if (!isDebug||content==null) {
            return;
        }
        String tag = generateTag();
        Log.d(tag, content.toString());
    }

    public static void d(Object content, Throwable tr) {
        if (!isDebug||content==null) {
            return;
        }
        String tag = generateTag();
        Log.d(tag, content.toString(), tr);
    }

    public static void e(Object content) {
        if (!isDebug||content==null) {
            return;
        }
        String tag = generateTag();
        Log.e(tag, content.toString());
    }

    public static void e(Object content, Throwable tr) {
        if (!isDebug||content==null) {
            return;
        }
        String tag = generateTag();
        Log.e(tag, content.toString(), tr);
    }

    public static void i(Object content) {
        if (!isDebug||content==null) {
            return;
        }
        String tag = generateTag();
        Log.i(tag, content.toString());
    }

    public static void i(Object content, Throwable tr) {
        if (!isDebug||content==null) {
            return;
        }
        String tag = generateTag();
        Log.i(tag, content.toString(), tr);
    }
}
