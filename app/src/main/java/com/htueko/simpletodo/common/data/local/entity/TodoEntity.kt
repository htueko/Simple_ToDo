package com.htueko.simpletodo.common.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htueko.simpletodo.common.data.local.LocalConstant
import kotlinx.datetime.LocalDateTime


@Entity(tableName = LocalConstant.TABLE_TODO)
data class TodoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val desc: String?,
    val hasComplete: Boolean = false,
    val isImportant: Boolean = false,
    val isUrgent: Boolean = false,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
)