package com.dyno.androidtodostarter.data.di

import com.dyno.androidtodostarter.data.TodoRepository
import com.dyno.androidtodostarter.data.source.DefaultTodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    internal abstract fun bindTodoRepository(
        repository: DefaultTodoRepository,
    ): TodoRepository
}
