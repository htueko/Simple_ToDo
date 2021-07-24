package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

interface GetTodosUseCase {
    operator fun invoke(): Flow<State<List<Todo>>>
}