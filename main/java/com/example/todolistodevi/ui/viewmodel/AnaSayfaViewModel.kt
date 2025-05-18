package com.example.todolistodevi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistodevi.data.entity.ToDoList
import com.example.todolistodevi.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(var toDoRepository: ToDoRepository): ViewModel() {
    var toDoList = MutableLiveData<List<ToDoList>>()

    init {
        listeYukle()
    }

    fun sil(id: Int){
        CoroutineScope(Dispatchers.Main).launch {
            toDoRepository.sil(id)
            listeYukle()
        }
    }
    fun listeYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            toDoList.value = toDoRepository.listeYukle()
        }
    }
    fun ara(aramaKelimesi: String){
        CoroutineScope(Dispatchers.Main).launch {
            toDoList.value = toDoRepository.ara(aramaKelimesi)
        }
    }
}