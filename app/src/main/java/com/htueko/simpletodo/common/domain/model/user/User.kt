package com.htueko.simpletodo.common.domain.model.user

import com.htueko.simpletodo.common.domain.model.todo.Todo

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val imageUrl: String,
    val todos: List<Todo>
)
