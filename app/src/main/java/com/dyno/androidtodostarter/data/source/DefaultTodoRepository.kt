package com.dyno.androidtodostarter.data.source

import com.dyno.androidtodostarter.data.Todo
import com.dyno.androidtodostarter.data.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class DefaultTodoRepository @Inject constructor() : TodoRepository {
    override suspend fun save(todo: Todo) {
    }

    override fun findAll(): Flow<List<Todo>> {
        return flow {
            emit(listOf(Todo("", "", Date())))
        }
    }
}
