package com.example.todolistodevi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolistodevi.data.entity.ToDoList

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoList")
    suspend fun listeYukle(): List<ToDoList>

    @Insert
    suspend fun kaydet(listOge: ToDoList)

    @Update
    suspend fun guncelle(list: ToDoList)

    @Delete
    suspend fun sil(listOge: ToDoList)

    @Query("SELECT * FROM ToDoList WHERE adi LIKE '%'|| :aramaKelimesi || '%'")
    suspend fun ara(aramaKelimesi: String): List<ToDoList>
}