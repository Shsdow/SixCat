package com.sixcat.model.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @uthor: GY.LEE
 * @date: 2019-05-20
 */
@Entity(tableName = "tasklist")
class Task(
        var title: String,
        var content: String,
        var complete: Boolean,
        @ColumnInfo(name = "date")
        var date: Date
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

}

