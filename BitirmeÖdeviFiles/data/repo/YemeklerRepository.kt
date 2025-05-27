package com.example.pupilicabitirmeprojesi.data.repo

import com.example.pupilicabitirmeprojesi.data.datasource.YemeklerDataSource
import com.example.pupilicabitirmeprojesi.data.entity.SepettekiYemekler
import com.example.pupilicabitirmeprojesi.data.entity.Yemekler

class YemeklerRepository(var yemeklerDataSource: YemeklerDataSource) {

    suspend fun yemekleriYukle() : List<Yemekler> = yemeklerDataSource.yemekleriYukle()
    suspend fun sepettekiYemekleriYukle(kullanici_adi: String) : List<SepettekiYemekler> = yemeklerDataSource.sepettekiYemekleriYukle(kullanici_adi)
    suspend fun sil(sepet_yemek_id: String,kullanici_adi: String) = yemeklerDataSource.sil(sepet_yemek_id,kullanici_adi)
    suspend fun sepeteEkle(yemek_id:Int,yemek_adi: String,
                           yemek_resim_adi: String,
                           yemek_fiyat: Int,
                           yemek_siparis_adet: Int,
                           kullanici_adi: String) = yemeklerDataSource.sepeteEkle(yemek_id,yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
}