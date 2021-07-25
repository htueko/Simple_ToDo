package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.data.usecase.DeleteTodoUseCaseImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for delete UseCase
 * @see [DeleteTodoUseCaseImpl]
 */
interface DeleteTodoUseCase {
    operator fun invoke(todo: Todo): Flow<State<Boolean>>
}
