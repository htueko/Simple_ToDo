package com.htueko.simpletodo.common.data.di

import android.content.Context
import androidx.room.Room
import com.htueko.simpletodo.common.data.local.LocalConstant
import com.htueko.simpletodo.common.data.local.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    // to provide database
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        LocalConstant.DB_NAME
    ).build()

    // to provide dao
    @Singleton
    @Provides
    fun provideTodoDao(db: TodoDatabase) = db.getTodoDao()
}
