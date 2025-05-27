package com.example.pupilicabitirmeprojesi.data.entity

import com.google.gson.annotations.SerializedName


data class SepettekiYemeklerCevap(
    @SerializedName("sepet_yemekler") val sepettekiYemekler: List<SepettekiYemekler>,
    @SerializedName("success") val success: Int
)
