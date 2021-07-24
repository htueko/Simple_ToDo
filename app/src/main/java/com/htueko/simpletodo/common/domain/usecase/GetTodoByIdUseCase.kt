package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

interface GetTodoByIdUseCase {
    operator fun invoke(id: Long): Flow<State<Todo>>
}