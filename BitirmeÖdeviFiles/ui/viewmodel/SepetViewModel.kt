package com.example.pupilicabitirmeprojesi.ui.viewmodel

import androidx.lifecycle.*
import com.example.pupilicabitirmeprojesi.data.entity.SepettekiYemekler
import com.example.pupilicabitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(
    private val yemeklerRepository: YemeklerRepository
) : ViewModel() {

    private val _sepettekiYemeklerListesi = MutableLiveData<List<SepettekiYemekler>>()
    val sepettekiYemeklerListesi: LiveData<List<SepettekiYemekler>> = _sepettekiYemeklerListesi

    fun kullaniciAdiDegistigindeSepetiYukle(kullaniciAdi: String?) {
        if (kullaniciAdi.isNullOrBlank()) {
            _sepettekiYemeklerListesi.value = emptyList()
            return
        }
        viewModelScope.launch {
            try {
                val liste = withContext(Dispatchers.IO) {
                    yemeklerRepository.sepettekiYemekleriYukle(kullaniciAdi)
                }
                _sepettekiYemeklerListesi.value = liste
            } catch (e: Exception) {
                _sepettekiYemeklerListesi.value = emptyList()
                e.printStackTrace()
            }
        }
    }

    fun sil(sepet_yemek_id: String, kullanici_adi_itemdan_gelen: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    yemeklerRepository.sil(sepet_yemek_id, kullanici_adi_itemdan_gelen)
                }
                kullaniciAdiDegistigindeSepetiYukle(kullanici_adi_itemdan_gelen)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}