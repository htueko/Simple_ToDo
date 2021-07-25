package com.htueko.simpletodo.common.data.datasource

import com.htueko.simpletodo.common.data.local.dao.TodoDao
import com.htueko.simpletodo.common.data.local.entity.TodoEntity
import com.htueko.simpletodo.common.domain.datasource.LocalDataSource
import com.htueko.simpletodo.common.domain.executor.AppExecutor
import com.htueko.simpletodo.common.domain.model.todo.Todo
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * single source to data for local database operation
 * @see [AppExecutor]
 * @see [TodoDao]
 * @see [LocalDataSource]
 */
class LocalDataSourceImpl @Inject constructor(
    private val taskExecutor: AppExecutor,
    private val todoDao: TodoDao
) : LocalDataSource {
    // to execute on IO thread
    private val backGroundTask = taskExecutor.io

    @FlowPreview
    override fun getTodo(): Flow<List<Todo>> =
        todoDao.getTodos()
            .distinctUntilChanged()
            .flowOn(backGroundTask)
            .flatMapConcat { list ->
                val todoList: MutableList<Todo> = mutableListOf()
                flow {
                    list.forEach {
                        Timber.d("getTodo: local data: $it")
                        todoList.add(TodoEntity.toDomain(it))
                    }
                    emit(todoList)
                }
            }

    override fun getTodoById(id: Long): Flow<Todo> =
        todoDao.getTodoById(id)
            .distinctUntilChanged()
            .flowOn(backGroundTask)
            .map { TodoEntity.toDomain(it) }

    override suspend fun addToDo(todo: Todo) = withContext(backGroundTask) {
        Timber.d("addToDo: called")
        Timber.d("addToDo: data: $todo")
        todoDao.insertTodo(TodoEntity.fromDomain(todo))
    }

    override suspend fun removeToDo(todo: Todo) = withContext(backGroundTask) {
        Timber.d("removeToDo: called")
        Timber.d("removeToDo: data: $todo")
        todoDao.deleteTodo(TodoEntity.fromDomain(todo))
    }

    override suspend fun updateToDo(todo: Todo) = withContext(backGroundTask) {
        Timber.d("updateToDo: called")
        Timber.d("updateToDo: data: $todo")
        todoDao.updateTodo(TodoEntity.fromDomain(todo))
    }
}
