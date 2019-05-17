package com.sixcat.utils

import android.app.Activity
import android.content.Context
import java.util.Stack

/**
 * 通过 stack 管理 Activity 工具类
 * @author liguoying
 * @date 2017/12/5.
 */

class ActivityManager {

    companion object {
        private var activityStack: Stack<Activity>? = null
        private var instance: ActivityManager? = null

        fun getInstance(): ActivityManager {
            if (instance == null) {
                synchronized(ActivityManager::class) {
                    if (instance == null) {
                        instance = ActivityManager()
                    }
                }
            }
            return instance!!
        }
    }

    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    fun currentActivity(): Activity {
        return activityStack!!.lastElement() as Activity
    }

    @JvmOverloads
    fun finishActivity(activity: Activity? = activityStack!!.lastElement() as Activity) {
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
        }
    }

    fun finishActivity(cls: Class<*>) {
        val it = activityStack!!.iterator()
        while (it.hasNext()) {
            val activity = it.next() as Activity
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    fun finishAllActivity() {
        val size = activityStack!!.size
        for (i in 0 until size) {
            if (activityStack!![i] != null) {
                (activityStack!![i] as Activity).finish()
            }
        }
        activityStack!!.clear()
    }

    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            (context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager).killBackgroundProcesses(context.packageName)
            System.exit(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
