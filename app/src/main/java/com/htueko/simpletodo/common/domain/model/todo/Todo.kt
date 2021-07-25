package com.htueko.simpletodo.common.domain.model.todo

import android.os.Parcel
import android.os.Parcelable
import kotlinx.datetime.LocalDateTime

data class Todo(
    val id: Long,
    val title: String,
    val desc: String?,
    val hasComplete: Boolean = false,
    val isImportant: Boolean = false,
    val isUrgent: Boolean = false,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
)