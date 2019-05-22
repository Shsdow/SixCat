package com.sixcat.jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sixcat.model.bean.Task

/**
 * @uthor: GY.LEE
 * @date: 2019-05-20
 */
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("SELECT * FROM tasklist ORDER BY id")
    fun getAllTaskWith(): LiveData<List<Task>>

    @Query("SELECT * FROM tasklist WHERE id = :taskId")
    fun getTaskWithId(taskId: Int): LiveData<Task>

    @Query("SELECT * FROM tasklist WHERE title = :taskTitle")
    fun getTasksWithTitle(taskTitle: String): LiveData<List<Task>>
}