package com.htueko.simpletodo.common.data.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.domain.usecase.AddTodoUseCase
import com.htueko.simpletodo.common.util.State
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AddTodoUseCaseImpl @Inject constructor(
    private val repo: TodoRepository
) : AddTodoUseCase {
    override fun invoke(todo: Todo): Flow<State<Boolean>> = repo.addToDo(todo)
}