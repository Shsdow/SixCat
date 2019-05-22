package com.sixcat.jetpack.repository

import com.sixcat.jetpack.room.TaskDao
import com.sixcat.model.bean.Task

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 *
 * Repository 数据类的仓库，可以包含的数据源：数据库、互联网
 */
class TaskRepository private constructor(private val taskDao: TaskDao) {

    fun getAllTask() = taskDao.getAllTaskWith()

    fun getTaskById(id: Int) = taskDao.getTaskWithId(id)

    fun getTasksWithTitle(title: String) = taskDao.getTasksWithTitle(title)

    fun insertTask(task: Task) {
        cacheAndPerform(task) {
            taskDao.insertTask(task)
        }
    }

    /**
     * 第一个参数： Task
     * 第二个参数：参数为 Task 返回值为 Unit 的 Function
     */
    private inline fun cacheAndPerform(task: Task, perform: (Task) -> Unit) {
//        val cachedTask = Task(task.title, task.description, task.id).apply {
//            isCompleted = task.isCompleted
//        }
//        cachedTasks.put(cachedTask.id, cachedTask)
        perform(task)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(taskDao: TaskDao) =
                instance ?: synchronized(this) {
                    instance ?: TaskRepository(taskDao).also { instance = it }
                }
    }
}