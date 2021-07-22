package com.htueko.simpletodo.common.domain.model.todo

import kotlinx.datetime.LocalDateTime

data class Todo(
    val id: Long,
    val title: String,
    val desc: String,
    val isImportant: Boolean = false,
    val isUrgent: Boolean = false,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
)