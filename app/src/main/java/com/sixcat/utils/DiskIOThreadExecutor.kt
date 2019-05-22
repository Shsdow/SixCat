package com.sixcat.utils

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 * 线程池
 */
class DiskIOThreadExecutor : Executor {
    private val diskIO = Executors.newSingleThreadExecutor()
    override fun execute(command: Runnable?) {
        diskIO.execute(command)
    }
}