package com.example.todolistodevi.di

import android.content.Context
import androidx.room.Room
import com.example.todolistodevi.data.datasource.ToDoDataSource
import com.example.todolistodevi.data.repo.ToDoRepository
import com.example.todolistodevi.room.ToDoDao
import com.example.todolistodevi.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideToDoRepository(toDoDataSource: ToDoDataSource): ToDoRepository{
        return ToDoRepository(toDoDataSource)
    }
    @Provides
    @Singleton
    fun provideToDoDataSource(toDoDao: ToDoDao): ToDoDataSource{
        return ToDoDataSource(toDoDao)
    }
    @Provides
    @Singleton
    fun provideToDoDao(@ApplicationContext context: Context): ToDoDao{
        val veritabani = Room.databaseBuilder(context, Veritabani::class.java,"list.sqlite")
            .createFromAsset("list.sqlite")
            .build()
        return veritabani.getToDoDao()
    }
}