package com.example.pupilicabitirmeprojesi.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class GecmisYemekler(
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int,
    val yemek_siparis_adet: Int,
    val silinecekZaman: Long,
    var kullaniciAdi: String
): Parcelable
