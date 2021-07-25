package com.htueko.simpletodo.common.data.remote.mapper

import com.htueko.simpletodo.common.data.remote.mapper.base.RemoteMapper
import com.htueko.simpletodo.common.data.remote.model.RemoteTodoModel
import com.htueko.simpletodo.common.domain.model.todo.Todo
import javax.inject.Inject

class RemoteTodoMapper @Inject constructor() : RemoteMapper<RemoteTodoModel, Todo> {

    override fun mapToDomain(remoteEntity: RemoteTodoModel): Todo {
        return Todo(
            id = remoteEntity.id,
            title = remoteEntity.title,
            desc = remoteEntity.desc.orEmpty(),
            hasComplete = remoteEntity.hasComplete.or(false),
            isImportant = remoteEntity.isImportant.or(false),
            isUrgent = remoteEntity.isUrgent.or(false),
            createAt = remoteEntity.createAt,
            updateAt = remoteEntity.updateAt
        )
    }
}
