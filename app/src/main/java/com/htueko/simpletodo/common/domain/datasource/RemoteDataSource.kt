package com.htueko.simpletodo.common.domain.datasource

import com.htueko.simpletodo.common.data.datasource.RemoteDataSourceImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for Remote DataSource
 * @see [RemoteDataSourceImpl]
 */
interface RemoteDataSource {
    fun getTodo(): Flow<List<Todo>>
    fun getTodoById(id: Long): Flow<Todo>
    suspend fun addToDo(todo: Todo)
    suspend fun removeToDo(todo: Todo)
    suspend fun updateToDo(todo: Todo)
}