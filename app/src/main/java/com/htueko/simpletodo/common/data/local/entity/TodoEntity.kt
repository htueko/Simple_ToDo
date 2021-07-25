package com.htueko.simpletodo.common.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htueko.simpletodo.common.data.local.LocalConstant
import com.htueko.simpletodo.common.domain.model.todo.Todo
import kotlinx.datetime.LocalDateTime
import timber.log.Timber

/**
 * model class to store data in local database
 * @see [Entity]
 * @see [LocalConstant]
 */
@Entity(tableName = LocalConstant.TABLE_TODO)
data class TodoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val desc: String?,
    val hasComplete: Boolean = false,
    val isImportant: Boolean = false,
    val isUrgent: Boolean = false,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
) {
    companion object {
        fun fromDomain(todo: Todo): TodoEntity {
            Timber.d("fromDomain: called with data: $todo")
            return TodoEntity(
                id = todo.id,
                title = todo.title,
                desc = todo.desc,
                hasComplete = todo.hasComplete,
                isImportant = todo.isImportant,
                isUrgent = todo.isUrgent,
                createAt = todo.createAt,
                updateAt = todo.updateAt
            )
        }

        fun toDomain(todoEntity: TodoEntity): Todo {
            Timber.d("toDomain: called with data: $todoEntity")
            return Todo(
                id = todoEntity.id,
                title = todoEntity.title,
                desc = todoEntity.desc,
                hasComplete = todoEntity.hasComplete,
                isImportant = todoEntity.isImportant,
                isUrgent = todoEntity.isUrgent,
                createAt = todoEntity.createAt,
                updateAt = todoEntity.updateAt
            )
        }
    }
}
