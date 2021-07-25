package com.htueko.simpletodo.common.data.usecase

import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import com.htueko.simpletodo.common.domain.usecase.GetTodosUseCase
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodosUseCaseImpl @Inject constructor(
    private val repo: TodoRepository
) : GetTodosUseCase {
    override fun invoke(): Flow<State<List<Todo>>> = repo.getTodo()
}
