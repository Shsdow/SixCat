package com.sixcat.jetpack.room

import androidx.room.TypeConverter
import java.util.*


/**
 * @uthor: GY.LEE
 * @date: 2019-05-23
 */
class DateTypeConvert {

    @TypeConverter
    fun fromDateToLong(date: Date?): Long? {
        return (date?.time)!!.toLong()
    }

    @TypeConverter
    fun fromLongToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }
}