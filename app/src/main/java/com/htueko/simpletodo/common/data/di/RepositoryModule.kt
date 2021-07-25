package com.htueko.simpletodo.common.data.di

import com.htueko.simpletodo.common.data.datasource.LocalDataSourceImpl
import com.htueko.simpletodo.common.data.datasource.RemoteDataSourceImpl
import com.htueko.simpletodo.common.data.executor.AppExecutorImpl
import com.htueko.simpletodo.common.data.repository.TodoRepositoryImpl
import com.htueko.simpletodo.common.domain.datasource.LocalDataSource
import com.htueko.simpletodo.common.domain.datasource.RemoteDataSource
import com.htueko.simpletodo.common.domain.executor.AppExecutor
import com.htueko.simpletodo.common.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // to provide remote datasource
    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    // to provide local datasource
    @Binds
    @Singleton
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    // to provide repository
    @Binds
    @Singleton
    abstract fun provideRepository(repository: TodoRepositoryImpl): TodoRepository

    // to provide task executor
    @Binds
    @Singleton
    abstract fun provideTaskExecutor(appExecutorImpl: AppExecutorImpl): AppExecutor
}
