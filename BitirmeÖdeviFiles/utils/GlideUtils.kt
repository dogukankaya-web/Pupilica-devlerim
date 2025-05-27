package com.example.pupilicabitirmeprojesi.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.resimGoster(resimAdi: String) {
    val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
    Glide.with(this.context)
        .load(url)
        .override(400, 400)
        .into(this)
}