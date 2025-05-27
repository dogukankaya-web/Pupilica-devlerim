package com.example.pupilicabitirmeprojesi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AktifKullaniciViewModel @Inject constructor() : ViewModel() {
    private val _aktifKullaniciAdi =
        MutableLiveData<String?>()
    val aktifKullaniciAdi: LiveData<String?> = _aktifKullaniciAdi

    fun setAktifKullaniciAdi(kullaniciAdi: String?) {
        if (_aktifKullaniciAdi.value != kullaniciAdi) {
            _aktifKullaniciAdi.value = kullaniciAdi
        }
    }
}