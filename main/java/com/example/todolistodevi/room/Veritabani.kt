package com.example.todolistodevi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistodevi.data.entity.ToDoList

@Database(entities = [ToDoList::class], version = 1)
abstract class Veritabani: RoomDatabase() {
    abstract fun getToDoDao(): ToDoDao
}