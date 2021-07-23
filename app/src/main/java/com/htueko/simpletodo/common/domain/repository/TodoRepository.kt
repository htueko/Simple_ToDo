package com.htueko.simpletodo.common.domain.repository

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodo(): Flow<State<List<Todo>>>
    fun getTodoById(id: Long): Flow<State<Todo>>
    fun addToDo(todo: Todo): Flow<State<Boolean>>
    fun removeToDo(todo: Todo): Flow<State<Boolean>>
    fun updateToDo(todo: Todo): Flow<State<Boolean>>
}