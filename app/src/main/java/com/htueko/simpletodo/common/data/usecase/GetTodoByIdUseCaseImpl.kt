package com.htueko.simpletodo.common.data.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.domain.usecase.GetTodoByIdUseCase
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoByIdUseCaseImpl @Inject constructor(
    private val repo: TodoRepository
) : GetTodoByIdUseCase {
    override fun invoke(id: Long): Flow<State<Todo>> = repo.getTodoById(id)
}
