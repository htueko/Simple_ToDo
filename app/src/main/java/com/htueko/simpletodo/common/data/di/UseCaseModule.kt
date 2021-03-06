package com.htueko.simpletodo.common.data.di

import com.htueko.simpletodo.common.data.usecase.AddTodoUseCaseImpl
import com.htueko.simpletodo.common.data.usecase.DeleteTodoUseCaseImpl
import com.htueko.simpletodo.common.data.usecase.GetTodoByIdUseCaseImpl
import com.htueko.simpletodo.common.data.usecase.GetTodosUseCaseImpl
import com.htueko.simpletodo.common.data.usecase.UpdateTodoUseCaseImpl
import com.htueko.simpletodo.common.domain.usecase.AddTodoUseCase
import com.htueko.simpletodo.common.domain.usecase.DeleteTodoUseCase
import com.htueko.simpletodo.common.domain.usecase.GetTodoByIdUseCase
import com.htueko.simpletodo.common.domain.usecase.GetTodosUseCase
import com.htueko.simpletodo.common.domain.usecase.UpdateTodoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    // to provide add use case
    @Binds
    @Singleton
    abstract fun provideAddTodoUseCase(addTodoUseCaseImpl: AddTodoUseCaseImpl): AddTodoUseCase

    // to provide delete use case
    @Binds
    @Singleton
    abstract fun provideDeleteTodoUseCase(deleteTodoUseCaseImpl: DeleteTodoUseCaseImpl): DeleteTodoUseCase

    // to provide get by id use case
    @Binds
    @Singleton
    abstract fun provideGetTodoByIdUseCase(getTodoByIdUseCaseImpl: GetTodoByIdUseCaseImpl): GetTodoByIdUseCase

    // to provide get all data use case
    @Binds
    @Singleton
    abstract fun provideGetTodosUseCase(getTodosUseCaseImpl: GetTodosUseCaseImpl): GetTodosUseCase

    // to provide update the data use case
    @Binds
    @Singleton
    abstract fun provideUpdateTodoUseCase(updateTodoUseCaseImpl: UpdateTodoUseCaseImpl): UpdateTodoUseCase
}
