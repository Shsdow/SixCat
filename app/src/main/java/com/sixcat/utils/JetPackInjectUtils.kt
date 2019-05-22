package com.sixcat.utils

import android.app.Application
import android.content.Context
import com.sixcat.jetpack.repository.TaskRepository
import com.sixcat.jetpack.room.TaskDatabase
import com.sixcat.jetpack.viewmodel.ViewModelFactory

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 * 获得 Repository 和 ViewModel 工厂 的工具类
 */
object JetPackInjectUtils {


    /**
     * 获得 Task 的 Repository
     */
    fun getTaskRepository(context: Context): TaskRepository =
            TaskRepository.getInstance(TaskDatabase.getInstance(context).getTaskDao())


    fun getTaskViewModelFactory(context: Application) = ViewModelFactory.getInstance(context)

}