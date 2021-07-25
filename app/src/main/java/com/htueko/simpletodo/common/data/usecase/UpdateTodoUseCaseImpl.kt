package com.htueko.simpletodo.common.data.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.domain.usecase.UpdateTodoUseCase
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTodoUseCaseImpl @Inject constructor(
    private val repo: TodoRepository
) : UpdateTodoUseCase {
    override fun invoke(todo: Todo): Flow<State<Boolean>> = repo.updateToDo(todo)
}
