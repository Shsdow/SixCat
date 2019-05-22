package com.sixcat.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sixcat.model.bean.Task
import com.sixcat.utils.DATABASE_NAME

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 */
@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object{

        @Volatile private var instance : TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase{
            return instance?: synchronized(this){
                instance ?: buildInstance(context).also { instance = it }
            }
        }

        private fun buildInstance(context: Context): TaskDatabase {
            return Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME)
//                    .addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                            WorkManager.getInstance().enqueue(request)
//                        }
//                    })
                    .build()
        }
    }
}