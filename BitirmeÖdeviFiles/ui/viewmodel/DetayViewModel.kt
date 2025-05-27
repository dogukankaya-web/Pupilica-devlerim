package com.example.pupilicabitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pupilicabitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository): ViewModel() {

    fun sepeteEkle(
        yemek_id: Int,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String,
        onSuccess: () -> Unit
    ){
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerRepository.sepeteEkle(yemek_id,
                                          yemek_adi,
                                          yemek_resim_adi,
                                          yemek_fiyat,
                                          yemek_siparis_adet,
                                          kullanici_adi)
            onSuccess()
        }
    }
}