package com.htueko.simpletodo.common.data.remote.model

import kotlinx.datetime.LocalDateTime

class RemoteTodoModel(
    val id: Long,
    val title: String,
    val desc: String?,
    val hasComplete: Boolean = false,
    val isImportant: Boolean = false,
    val isUrgent: Boolean = false,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
)