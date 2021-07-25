package com.htueko.simpletodo.common.presentation.add.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.usecase.AddTodoUseCase
import com.htueko.simpletodo.common.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val addTodoUseCase: AddTodoUseCase
) : ViewModel() {

    private val _updateUIFlow: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.loading())
    val updateUIFlow: StateFlow<State<Boolean>>
        get() = this._updateUIFlow

    fun addTodo(todo: Todo) = viewModelScope.launch {
        addTodoUseCase.invoke(todo)
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
