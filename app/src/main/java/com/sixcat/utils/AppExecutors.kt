package com.sixcat.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 */

const val THREAD_NUM = 3

open class AppExecutors(val diskIO: Executor = DiskIOThreadExecutor(),
                        val networkIO: Executor = Executors.newFixedThreadPool(THREAD_NUM),
                        val mainIO: Executor = MainIOThreadExecutor()) {

    private class MainIOThreadExecutor : Executor {
        private val mainHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            mainHandler.post(command)
        }

    }
}