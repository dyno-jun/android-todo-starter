package com.dyno.androidtodostarter.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun save(todo: Todo)

    fun findAll(): Flow<List<Todo>>
}
