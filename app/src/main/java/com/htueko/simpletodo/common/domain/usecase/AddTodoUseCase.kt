package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.data.usecase.AddTodoUseCaseImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for insert UseCase
 * @see [AddTodoUseCaseImpl]
 */
interface AddTodoUseCase {
    operator fun invoke(todo: Todo): Flow<State<Boolean>>
}
