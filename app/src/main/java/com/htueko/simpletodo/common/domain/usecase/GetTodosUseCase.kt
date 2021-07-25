package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.data.usecase.GetTodosUseCaseImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement to get all data
 * @see [GetTodosUseCaseImpl]
 */
interface GetTodosUseCase {
    operator fun invoke(): Flow<State<List<Todo>>>
}
