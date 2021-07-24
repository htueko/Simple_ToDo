package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

interface DeleteTodoUseCase {
    operator fun invoke(todo: Todo): Flow<State<Boolean>>
}