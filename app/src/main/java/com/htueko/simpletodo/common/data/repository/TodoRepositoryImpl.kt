package com.htueko.simpletodo.common.data.repository

import com.htueko.simpletodo.common.domain.datasource.LocalDataSource
import com.htueko.simpletodo.common.domain.datasource.RemoteDataSource
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.util.State
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : TodoRepository {

    override fun getTodo(): Flow<State<List<Todo>>> = flow<State<List<Todo>>> {
        remote.getTodo()
            .catch { emit(State.error(it.localizedMessage)) }
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        emit(State.error(state.message))
                    }
                    is State.Loading -> {
                        emit(State.loading())
                    }
                    is State.Success -> {
                        state.data.forEach { local.updateToDo(it) }
                        emit(State.success(state.data))
                    }
                }
            }
    }.catch { emit(State.error(it.localizedMessage)) }

    override fun getTodoById(id: Long): Flow<State<Todo>> = flow<State<Todo>> {
        local.getTodo()
            .catch { emit(State.error(it.localizedMessage)) }
            .map {
                val data = it.find { todo -> todo.id == id }
                if (data != null) {
                    emit(State.success(data))
                } else {
                    remote.getTodoById(id)
                        .catch { error -> emit(State.error(error?.localizedMessage)) }
                        .collect { state ->
                            when (state) {
                                is State.Error -> {
                                    emit(State.error(state.message))
                                }
                                is State.Loading -> {
                                    emit(State.loading())
                                }
                                is State.Success -> {
                                    emit(State.success(state.data))
                                }
                            }
                        }
                }
            }
    }.catch {
        emit(State.error(it.localizedMessage))
    }

    override fun addToDo(todo: Todo): Flow<State<Boolean>> = flow<State<Boolean>> {
        remote.addToDo(todo)
            .catch {
                emit(State.error(it.localizedMessage))
            }
            .collect {
                when (it) {
                    is State.Error -> {
                        emit(State.error(it.message))
                    }
                    is State.Loading -> {
                        emit(State.loading())
                    }
                    is State.Success -> {
                        if (it.data) {
                            local.addToDo(todo)
                        }
                    }
                }
            }
    }.catch {
        emit(State.error(it.localizedMessage))
    }

    override fun removeToDo(todo: Todo) = flow<State<Boolean>> {
        remote.removeToDo(todo)
            .catch {
                emit(State.error(it.localizedMessage))
            }
            .collect {
                when (it) {
                    is State.Error -> {
                        emit(State.error(it.message))
                    }
                    is State.Loading -> {
                        emit(State.loading())
                    }
                    is State.Success -> {
                        if (it.data) {
                            local.removeToDo(todo)
                        }
                    }
                }
            }

    }.catch {
        emit(State.error(it.localizedMessage))
    }

    override fun updateToDo(todo: Todo) = flow<State<Boolean>> {
        remote.updateToDo(todo)
            .catch {
                emit(State.error(it.localizedMessage))
            }
            .collect {
                when (it) {
                    is State.Error -> {
                        emit(State.error(it.message))
                    }
                    is State.Loading -> {
                        emit(State.loading())
                    }
                    is State.Success -> {
                        if (it.data) {
                            local.updateToDo(todo)
                        }
                    }
                }
            }
    }.catch {
        emit(State.error(it.localizedMessage))
    }

}