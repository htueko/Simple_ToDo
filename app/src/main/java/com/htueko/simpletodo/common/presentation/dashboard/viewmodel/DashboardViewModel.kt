package com.htueko.simpletodo.common.presentation.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.usecase.DeleteTodoUseCase
import com.htueko.simpletodo.common.domain.usecase.GetTodosUseCase
import com.htueko.simpletodo.common.domain.usecase.UpdateTodoUseCase
import com.htueko.simpletodo.common.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {

    private val _todos: MutableStateFlow<State<List<Todo>>> = MutableStateFlow(State.loading())
    val todos: StateFlow<State<List<Todo>>>
        get() = this._todos

    private val _updateUIFlow: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.loading())
    val updateUIFlow: StateFlow<State<Boolean>>
        get() = this._updateUIFlow

    fun getTodos() = viewModelScope.launch {
        Timber.d("getTodos: called")
        getTodosUseCase.invoke()
            .collect { state ->
                Timber.d("getTodos: collect $state")
                when (state) {
                    is State.Error -> {
                        Timber.d("getTodos: collect $state")
                        _todos.value = State.error(state.message)
                        Timber.d("getTodos: value: ${_todos.value}")
                    }
                    is State.Loading -> {
                        Timber.d("getTodos: collect $state")
                        _todos.value = State.loading()
                        Timber.d("getTodos: value: ${_todos.value}")
                    }
                    is State.Success -> {
                        Timber.d("getTodos: collect $state")
                        _todos.value = State.success(state.data)
                        Timber.d("getTodos: value: ${_todos.value}")
                    }
                }
            }
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        deleteTodoUseCase.invoke(todo)
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        _updateUIFlow.value = State.error(state.message)
                    }
                    is State.Loading -> {
                        _updateUIFlow.value = State.loading()
                    }
                    is State.Success -> {
                        _updateUIFlow.value = State.success(state.data)
                    }
                }
            }
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch {
        updateTodoUseCase.invoke(todo)
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        _updateUIFlow.value = State.error(state.message)
                    }
                    is State.Loading -> {
                        _updateUIFlow.value = State.loading()
                    }
                    is State.Success -> {
                        _updateUIFlow.value = State.success(state.data)
                    }
                }
            }
    }
}
