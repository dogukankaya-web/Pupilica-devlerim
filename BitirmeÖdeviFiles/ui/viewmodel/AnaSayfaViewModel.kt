package com.example.pupilicabitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pupilicabitirmeprojesi.data.entity.Yemekler
import com.example.pupilicabitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnaSayfaViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository): ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private var tumYemekler = listOf<Yemekler>()

    init {
        yemekleriYukle()
    }

    fun filtreleKategoriyeGore(kategori: String) {
        if (kategori == "Tümü") {
            yemeklerListesi.value = tumYemekler
        } else {
            yemeklerListesi.value = tumYemekler.filter {
                yemekKategoriBelirle(it) == kategori
            }
        }
    }

    private fun yemekKategoriBelirle(yemek: Yemekler): String { //liste büyürse çakışmalar oluşabilir
        val ad = yemek.yemek_adi.lowercase()

        return when {
            ad.contains("somon") || ad.contains("tavuk") || ad.contains("köfte") || ad.contains("lazanya") || ad.contains("makarna") || ad.contains("pizza") -> "Yemek"
            ad.contains("kadayıf") || ad.contains("tirami") || ad.contains("sütlaç") || ad.contains("baklava") -> "Tatlı"
            ad.contains("su") || ad.contains("fanta") || ad.contains("ayran") || ad.contains("kahve") -> "İçecek"
            else -> "Yemek" // varsayılan
        }
    }

    fun ara(query: String) {
        if (query.isBlank()) {
            yemeklerListesi.value = tumYemekler
        } else {
            yemeklerListesi.value = tumYemekler.filter {
                it.yemek_adi.contains(query, ignoreCase = true)
            }
        }
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val liste = yemeklerRepository.yemekleriYukle()
                tumYemekler = liste
                yemeklerListesi.value = liste
                Log.d("AnaSayfaViewModel", "Yemek listesi: ${liste.size}")
            } catch (e: Exception) {
                Log.e("AnaSayfaViewModel", "Hata: ${e.message}")
            }
        }
    }
}