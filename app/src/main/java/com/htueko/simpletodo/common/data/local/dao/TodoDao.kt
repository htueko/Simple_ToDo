package com.htueko.simpletodo.common.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.htueko.simpletodo.common.data.local.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTodo(todoEntity: TodoEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todoEntity: TodoEntity)

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity)

    @Query("select * from todo_table order by createAt asc")
    fun getTodos(): Flow<List<TodoEntity>>

    @Query("select * from todo_table where id = :todoId")
    fun getTodoById(todoId: Long): Flow<List<TodoEntity>>

}