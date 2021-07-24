package com.htueko.simpletodo.common.data.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.domain.usecase.UpdateTodoUseCase
import com.htueko.simpletodo.common.util.State
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UpdateTodoUseCaseImpl @Inject constructor(
    private val repo: TodoRepository
) : UpdateTodoUseCase {
    override fun invoke(todo: Todo): Flow<State<Boolean>> = repo.updateToDo(todo)
}