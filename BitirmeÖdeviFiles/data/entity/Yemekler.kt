package com.example.pupilicabitirmeprojesi.data.entity

import java.io.Serializable

data class Yemekler(val yemek_id: Int,
                    val yemek_adi: String,
                    val yemek_resim_adi: String,
                    val yemek_fiyat: Int): Serializable {
}