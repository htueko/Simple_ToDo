package com.htueko.simpletodo.common.domain.model.todo

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Todo(
    val id: Long = 1L,
    val title: String = "",
    val desc: String? = null,
    val hasComplete: Boolean = false,
    val isImportant: Boolean = false,
    val isUrgent: Boolean = false,
    val createAt: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    val updateAt: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
)
