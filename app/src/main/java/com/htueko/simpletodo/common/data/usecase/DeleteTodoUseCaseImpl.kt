package com.htueko.simpletodo.common.data.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.domain.usecase.DeleteTodoUseCase
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTodoUseCaseImpl @Inject constructor(
    private val repo: TodoRepository
) : DeleteTodoUseCase {
    override fun invoke(todo: Todo): Flow<State<Boolean>> = repo.removeToDo(todo)
}
