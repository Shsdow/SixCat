package com.sixcat.jetpack.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sixcat.jetpack.repository.TaskRepository
import com.sixcat.jetpack.room.TaskDatabase

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 *
 * 不同的 ViewModel 的工厂类
 */
class ViewModelFactory(
        private val taskRepository: TaskRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(TaskViewModel::class.java) -> TaskViewModel(taskRepository)
                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(
                            TaskRepository.getInstance(TaskDatabase.getInstance(application).getTaskDao()))
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}