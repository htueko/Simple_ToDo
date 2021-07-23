package com.htueko.simpletodo.common.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.htueko.simpletodo.common.data.datasource.RemoteDataSourceImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for Remote DataSource
 * @see [RemoteDataSourceImpl]
 */
interface RemoteDataSource {
    fun getTodo(): Flow<State<List<Todo>>>
    fun getTodoById(id: Long): Flow<State<Todo>>
    fun addToDo(todo: Todo) : Flow<State<Boolean>>
    fun removeToDo(todo: Todo): Flow<State<Boolean>>
    fun updateToDo(todo: Todo): Flow<State<Boolean>>
}