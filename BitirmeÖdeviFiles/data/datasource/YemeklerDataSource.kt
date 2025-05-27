package com.example.pupilicabitirmeprojesi.data.datasource

import android.util.Log
import com.example.pupilicabitirmeprojesi.data.entity.SepettekiYemekler
import com.example.pupilicabitirmeprojesi.data.entity.Yemekler
import com.example.pupilicabitirmeprojesi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var yemeklerDao: YemeklerDao) {
    suspend fun sepeteEkle(yemek_id: Int,
                           yemek_adi: String,
                           yemek_resim_adi: String,
                           yemek_fiyat: Int,
                           yemek_siparis_adet: Int,
                           kullanici_adi: String){
        val crudCevap =
            yemeklerDao
                .sepeteEkle(yemek_id,
                            yemek_adi,
                            yemek_resim_adi,
                            yemek_fiyat,
                            yemek_siparis_adet,
                            kullanici_adi)
        Log.d("DataSource", "Sepete ekle cevap: ${crudCevap.success} - ${crudCevap.message}")
    }
    suspend fun sil(sepet_yemek_id: String,kullanici_adi: String){
        val crudCevap = yemeklerDao.sil(sepet_yemek_id,kullanici_adi)
    }
    suspend fun yemekleriYukle() : List<Yemekler> = withContext(Dispatchers.IO){
        return@withContext yemeklerDao.yemekleriYukle().yemekler
    }
    suspend fun sepettekiYemekleriYukle(kullanici_adi: String) : List<SepettekiYemekler> = withContext(Dispatchers.IO) {
        val response = yemeklerDao.sepettekiYemekleriYukle(kullanici_adi)
        Log.d("DataSource", "Sepetteki yemekler count: ${response.sepettekiYemekler.size}")
        return@withContext response.sepettekiYemekler
    }

}