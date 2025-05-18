package com.example.todolistodevi.data.datasource

import com.example.todolistodevi.data.entity.ToDoList
import com.example.todolistodevi.room.ToDoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoDataSource(var toDoDao: ToDoDao) {
    suspend fun kaydet(adi: String,aciklama : String?){
        val yeniOge = ToDoList(0,adi,aciklama)
        toDoDao.kaydet(yeniOge)
    }
    suspend fun guncelle(id: Int,adi: String,aciklama: String?){
        val gulcelOge = ToDoList(id,adi,aciklama)
        toDoDao.guncelle(gulcelOge)
    }
    suspend fun sil(id: Int){
        val silinenOge = ToDoList(id,"","")
        toDoDao.sil(silinenOge)
    }
    suspend fun listeYukle(): List<ToDoList> = withContext(Dispatchers.IO){
        return@withContext toDoDao.listeYukle()
    }
    suspend fun ara(aramaKelimesi: String): List<ToDoList> = withContext(Dispatchers.IO){
        return@withContext toDoDao.ara(aramaKelimesi)
    }
}