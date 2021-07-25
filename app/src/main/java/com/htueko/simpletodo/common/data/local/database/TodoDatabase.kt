package com.htueko.simpletodo.common.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.htueko.simpletodo.common.data.local.LocalConstant
import com.htueko.simpletodo.common.data.local.converter.LocalDateTimeConverter
import com.htueko.simpletodo.common.data.local.dao.TodoDao
import com.htueko.simpletodo.common.data.local.entity.TodoEntity

/**
 * for database operation
 * @see [RoomDatabase]
 * @see [LocalConstant]
 * @see [TodoDao]
 */
@Database(entities = [TodoEntity::class], exportSchema = false, version = LocalConstant.DB_VERSION)
@TypeConverters(LocalDateTimeConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}
