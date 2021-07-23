package com.htueko.simpletodo.common.data.datasource

import com.htueko.simpletodo.common.data.local.dao.TodoDao
import com.htueko.simpletodo.common.data.local.entity.TodoEntity
import com.htueko.simpletodo.common.domain.datasource.LocalDataSource
import com.htueko.simpletodo.common.domain.executor.AppExecutor
import com.htueko.simpletodo.common.domain.model.todo.Todo
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSourceImpl @Inject constructor(
    private val taskExecutor: AppExecutor,
    private val todoDao: TodoDao
) : LocalDataSource {

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
        todoDao.insertTodo(TodoEntity.fromDomain(todo))
    }

    override suspend fun removeToDo(todo: Todo) = withContext(backGroundTask) {
        todoDao.deleteTodo(TodoEntity.fromDomain(todo))
    }

    override suspend fun updateToDo(todo: Todo) = withContext(backGroundTask) {
        todoDao.updateTodo(TodoEntity.fromDomain(todo))
    }


}