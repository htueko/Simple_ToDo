package com.htueko.simpletodo.common.data.local.converter

import androidx.room.TypeConverter
import com.htueko.simpletodo.common.data.local.database.TodoDatabase
import kotlinx.datetime.LocalDateTime

/**
 * to convert LocalDateTime to String and from String
 * @see [TypeConverter]
 * @see [TodoDatabase]
 */
class LocalDateTimeConverter {

    /**
     * to convert LocalDateTime from String
     * @see [LocalDateTime]
     * @return [LocalDateTime]
     **/
    @TypeConverter
    fun fromString(localDateTime: String): LocalDateTime {
        return LocalDateTime.parse(localDateTime)
    }

    /**
     * to convert LocalDateTime to String
     * @see [LocalDateTime]
     * @return [String]
     */
    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }
}
