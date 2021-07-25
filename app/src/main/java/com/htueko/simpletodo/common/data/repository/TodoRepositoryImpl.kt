package com.htueko.simpletodo.common.data.repository

import com.htueko.simpletodo.common.domain.datasource.LocalDataSource
import com.htueko.simpletodo.common.domain.datasource.RemoteDataSource
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

/**
 * repository that used local and remote operation
 * @see [LocalDataSource]
 * @see [RemoteDataSource]
 * @see [TodoRepository]
 */
class TodoRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : TodoRepository {

    override fun getTodo(): Flow<State<List<Todo>>> = flow<State<List<Todo>>> {
        remote.getTodo()
            .catch { emit(State.error(it.localizedMessage)) }
            .collect { state ->
                Timber.d("getTodo: called")
                when (state) {
                    is State.Error -> {
                        Timber.d("getTodo: state: $state")
                        emit(State.error(state.message))
                    }
                    is State.Loading -> {
                        Timber.d("getTodo: state: $state")
                        emit(State.loading())
                    }
                    is State.Success -> {
                        Timber.d("getTodo: state: $state")
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
                Timber.d("getTodoById: data: $data")
                if (data != null) {
                    emit(State.success(data))
                } else {
                    remote.getTodoById(id)
                        .catch { error -> emit(State.error(error?.localizedMessage)) }
                        .collect { state ->
                            when (state) {
                                is State.Error -> {
                                    Timber.d("getTodoById: state: $state")
                                    emit(State.error(state.message))
                                }
                                is State.Loading -> {
                                    Timber.d("getTodoById: state: $state")
                                    emit(State.loading())
                                }
                                is State.Success -> {
                                    Timber.d("getTodoById: state: $state")
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
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        Timber.d("addToDo: state: $state")
                        emit(State.error(state.message))
                    }
                    is State.Loading -> {
                        Timber.d("addToDo: state: $state")
                        emit(State.loading())
                    }
                    is State.Success -> {
                        Timber.d("addToDo: state: $state")
                        if (state.data) {
                            Timber.d("addToDo: state data: ${state.data}")
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
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        Timber.d("removeToDo: state: $state")
                        emit(State.error(state.message))
                    }
                    is State.Loading -> {
                        Timber.d("removeToDo: state: $state")
                        emit(State.loading())
                    }
                    is State.Success -> {
                        Timber.d("removeToDo: state: $state")
                        if (state.data) {
                            Timber.d("removeToDo: state data: ${state.data}")
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
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        Timber.d("updateToDo: state: $state")
                        emit(State.error(state.message))
                    }
                    is State.Loading -> {
                        Timber.d("updateToDo: state: $state")
                        emit(State.loading())
                    }
                    is State.Success -> {
                        Timber.d("updateToDo: state: $state")
                        if (state.data) {
                            Timber.d("updateToDo: state data: ${state.data}")
                            local.updateToDo(todo)
                        }
                    }
                }
            }
    }.catch {
        emit(State.error(it.localizedMessage))
    }
}
