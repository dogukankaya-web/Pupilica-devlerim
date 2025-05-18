package com.example.todolistodevi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todolistodevi.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetayViewModel @Inject constructor(var toDoRepository: ToDoRepository) : ViewModel(){

    fun guncelle(id: Int,adi: String,aciklama: String?){
        CoroutineScope(Dispatchers.Main).launch {
            toDoRepository.guncelle(id,adi,aciklama)
        }

    }
}