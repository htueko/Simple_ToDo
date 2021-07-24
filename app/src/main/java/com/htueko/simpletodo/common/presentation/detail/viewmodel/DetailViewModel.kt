package com.htueko.simpletodo.common.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.htueko.simpletodo.common.domain.usecase.DeleteTodoUseCase
import com.htueko.simpletodo.common.domain.usecase.GetTodoByIdUseCase
import com.htueko.simpletodo.common.domain.usecase.UpdateTodoUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {
}