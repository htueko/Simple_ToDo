package com.htueko.simpletodo.common.presentation.update.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.usecase.DeleteTodoUseCase
import com.htueko.simpletodo.common.domain.usecase.GetTodoByIdUseCase
import com.htueko.simpletodo.common.domain.usecase.UpdateTodoUseCase
import com.htueko.simpletodo.common.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    private val _todo: MutableStateFlow<State<Todo>> = MutableStateFlow(State.loading())
    val todo: StateFlow<State<Todo>>
        get() = this._todo

    private val _updateUIFlow: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.loading())
    val updateUIFlow: StateFlow<State<Boolean>>
        get() = this._updateUIFlow

    fun getTodoById(id: Long) = viewModelScope.launch {
        getTodoByIdUseCase.invoke(id)
            .collect { state ->
                when (state) {
                    is State.Error -> {
                        _todo.value = State.error(state.message)
                    }
                    is State.Loading -> {
                        _todo.value = State.loading()
                    }
                    is State.Success -> {
                        _todo.value = State.success(state.data)
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
