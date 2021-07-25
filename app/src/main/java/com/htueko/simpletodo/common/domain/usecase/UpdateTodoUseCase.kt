package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.data.usecase.UpdateTodoUseCaseImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement to update the data
 * @see [UpdateTodoUseCaseImpl]
 */
interface UpdateTodoUseCase {
    operator fun invoke(todo: Todo): Flow<State<Boolean>>
}
