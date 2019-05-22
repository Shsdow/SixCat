package com.sixcat.model.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @uthor: GY.LEE
 * @date: 2019-05-20
 */
@Entity(tableName = "tasklist")
data class Task(@PrimaryKey @ColumnInfo(name = "id") var id: Int,
                val title: String,
                val content: String,
                var complete: Boolean)