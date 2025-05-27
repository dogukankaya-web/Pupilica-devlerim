package com.example.pupilicabitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pupilicabitirmeprojesi.data.entity.GecmisYemekler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GecmisViewModel @Inject constructor() : ViewModel() {
    private val _gecmisList = MutableLiveData<List<GecmisYemekler>>(emptyList())
    val gecmisList: LiveData<List<GecmisYemekler>> = _gecmisList

    private var zamanlayiciJob: Job? = null

    fun siparisiEkle(yeniGecmisSiparisler: List<GecmisYemekler>) {
        Log.d("GecmisViewModelLog", "siparisiEkle çağrıldı. Eklenecek öğe sayısı: ${yeniGecmisSiparisler.size}")

        if (yeniGecmisSiparisler.isEmpty()) {
            Log.d("GecmisViewModelLog", "Eklenecek yeni geçmiş sipariş yok, işlem yapılmadı.")
            return
        }

        val mevcutListe = _gecmisList.value ?: emptyList()
        _gecmisList.value = mevcutListe + yeniGecmisSiparisler
        Log.d("GecmisViewModelLog", "Geçmiş liste güncellendi. Yeni boyut: ${(_gecmisList.value ?: emptyList()).size}")

        if (zamanlayiciJob == null || zamanlayiciJob?.isActive == false) {
            baslatZamanlayiciSil()
        }
    }

    private fun baslatZamanlayiciSil() {
        if (zamanlayiciJob?.isActive == true) {
            Log.d("GecmisViewModelLog", "Zamanlayıcı zaten aktif, tekrar başlatılmıyor.")
            return
        }

        zamanlayiciJob = viewModelScope.launch {
            Log.d("GecmisViewModelLog", "ZamanlayıcıSil coroutine'i başlatıldı.")
            try {
                while (true) {
                    delay(30 * 1000) // 30 saniyede bir kontrol et

                    val simdi = System.currentTimeMillis()
                    val gecerliListe = _gecmisList.value ?: emptyList()

                    if (gecerliListe.isEmpty()) {
                        Log.d("GecmisViewModelLog", "Geçmiş listesi boş. Zamanlayıcı döngüsü devam ediyor ama işlem yok.")
                        continue
                    }

                    val filtrelenmisListe = gecerliListe.filter { it.silinecekZaman > simdi }

                    if (filtrelenmisListe.size != gecerliListe.size) {
                        Log.d("GecmisViewModelLog", "Süresi dolan ${gecerliListe.size - filtrelenmisListe.size} öğe silindi. Kalan öğe: ${filtrelenmisListe.size}")
                        _gecmisList.postValue(filtrelenmisListe)
                    } else {
                    }
                }
            } finally {
                Log.d("GecmisViewModelLog", "ZamanlayıcıSil coroutine'i sonlandı/iptal edildi.")
            }
        }
    }

    fun siparisIptal(yemek: GecmisYemekler) {
        Log.d("GecmisViewModelLog", "siparisIptal çağrıldı: ${yemek.yemek_adi}, Kullanıcı: ${yemek.kullaniciAdi}")
        val guncelListe = _gecmisList.value?.filter {
            it != yemek
        }
        _gecmisList.value = guncelListe ?: emptyList()
        Log.d("GecmisViewModelLog", "Sipariş iptal sonrası liste boyutu: ${(_gecmisList.value ?: emptyList()).size}")
    }

    fun gecmisTemizle(kullaniciAdi: String? = null) {
        val mevcutListeFull = _gecmisList.value ?: emptyList()
        if (mevcutListeFull.isEmpty()){
            Log.d("GecmisViewModelLog", "gecmisTemizle: Liste zaten boş, işlem yapılmadı.")
            return
        }

        if (kullaniciAdi == null) {
            Log.d("GecmisViewModelLog", "gecmisTemizle çağrıldı. Tüm kullanıcıların geçmişi siliniyor.")
            _gecmisList.value = emptyList()
        } else {
            Log.d("GecmisViewModelLog", "gecmisTemizle çağrıldı. Kullanıcı: '$kullaniciAdi' için geçmiş siliniyor.")
            val filtrelenmisListe = mevcutListeFull.filter { it.kullaniciAdi != kullaniciAdi }
            if (filtrelenmisListe.size < mevcutListeFull.size) {
                _gecmisList.value = filtrelenmisListe
                Log.d("GecmisViewModelLog", "Kullanıcı '$kullaniciAdi' için ${mevcutListeFull.size - filtrelenmisListe.size} öğe silindi.")
            } else {
                Log.d("GecmisViewModelLog", "Kullanıcı '$kullaniciAdi' için silinecek öğe bulunamadı.")
            }
        }
        Log.d("GecmisViewModelLog", "gecmisTemizle sonrası liste boyutu: ${(_gecmisList.value ?: emptyList()).size}")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GecmisViewModelLog", "onCleared çağrıldı. Zamanlayıcı (varsa) iptal edilecek.")
        zamanlayiciJob?.cancel()
        zamanlayiciJob = null
    }
}