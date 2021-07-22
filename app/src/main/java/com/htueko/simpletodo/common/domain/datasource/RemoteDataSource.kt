package com.htueko.simpletodo.common.domain.datasource

import com.htueko.simpletodo.common.domain.model.todo.Todo
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for Remote DataSource
 * @see [RemoteDataSourceImpl]
 */
interface RemoteDataSource {
    fun getTodo(): Flow<List<Todo>>
    fun addToDo(todo: Todo)
    fun removeToDo(todo: Todo)
    fun updateToDo(todo: Todo)
}