package com.sixcat.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.sixcat.jetpack.repository.TaskRepository
import com.sixcat.model.bean.Task

/**
 * @uthor: GY.LEE
 * @date: 2019-05-20
 */
class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val tasks = taskRepository.getAllTask()

    fun insertTaskToDatabase(task: Task) {
        taskRepository.insertTask(task)
    }
}