package com.htueko.simpletodo.common.data.remote.mapper.base

import com.htueko.simpletodo.common.domain.model.todo.Todo

/**
 * base interface for Remote Mapper
 * to convert remote model to domain model
 */
interface RemoteMapper<Entity, Domain> {
    fun mapToDomain(remoteEntity: Entity): Todo
}
