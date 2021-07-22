package com.htueko.simpletodo.common.data.local.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun fromString(localDateTime: String): LocalDateTime {
        return LocalDateTime.parse(localDateTime)
    }

    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }

}