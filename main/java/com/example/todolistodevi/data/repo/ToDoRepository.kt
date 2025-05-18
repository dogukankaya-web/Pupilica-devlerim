package com.example.todolistodevi.data.repo

import com.example.todolistodevi.data.datasource.ToDoDataSource
import com.example.todolistodevi.data.entity.ToDoList

class ToDoRepository(var toDoDataSource: ToDoDataSource) {

    suspend fun kaydet(adi: String,aciklama: String?){
        toDoDataSource.kaydet(adi,aciklama)
    }

    suspend fun guncelle(id: Int,adi: String,aciklama: String?){
        toDoDataSource.guncelle(id,adi,aciklama)
    }

    suspend fun sil(id: Int) = toDoDataSource.sil(id)

    suspend fun listeYukle(): List<ToDoList> = toDoDataSource.listeYukle()

    suspend fun ara(aramaKelimesi: String): List<ToDoList> = toDoDataSource.ara(aramaKelimesi)
}