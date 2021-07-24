package com.htueko.simpletodo.common.presentation.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.usecase.DeleteTodoUseCase
import com.htueko.simpletodo.common.domain.usecase.GetTodosUseCase
import com.htueko.simpletodo.common.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    private val _todos: MutableStateFlow<State<List<Todo>>> = MutableStateFlow(State.loading())
    val todos: StateFlow<State<List<Todo>>>
        get() = this._todos

    private val _deleteTodo: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.loading())
    val deleteTodo: StateFlow<State<Boolean>>
        get() = this._deleteTodo

    fun getTodos() = viewModelScope.launch {
        getTodosUseCase.invoke()
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        _todos.value = State.error(state.message)
                    }
                    is State.Loading -> {
                        _todos.value = State.loading()
                    }
                    is State.Success -> {
                        _todos.value = State.success(state.data)
                    }
                }
            }
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        deleteTodoUseCase.invoke(todo)
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        _deleteTodo.value = State.error(state.message)
                    }
                    is State.Loading -> {
                        _deleteTodo.value = State.loading()
                    }
                    is State.Success -> {
                        _deleteTodo.value = State.success(state.data)
                    }
                }
            }

    }



}