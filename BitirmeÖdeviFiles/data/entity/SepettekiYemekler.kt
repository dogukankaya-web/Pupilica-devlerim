package com.example.pupilicabitirmeprojesi.data.entity


import java.io.Serializable

data class SepettekiYemekler(val sepet_yemek_id: String,
                             val yemek_adi: String,
                             val yemek_resim_adi: String,
                             val yemek_fiyat: String,
                             val yemek_siparis_adet: String,
                             val kullanici_adi: String): Serializable  {
}