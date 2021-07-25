package com.htueko.simpletodo.common.domain.usecase

import com.htueko.simpletodo.common.data.usecase.GetTodoByIdUseCaseImpl
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow

/**
 * Contract to implement for get by id UseCase
 * @see [GetTodoByIdUseCaseImpl]
 */
interface GetTodoByIdUseCase {
    operator fun invoke(id: Long): Flow<State<Todo>>
}
