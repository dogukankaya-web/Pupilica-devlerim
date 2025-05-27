package com.example.pupilicabitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pupilicabitirmeprojesi.data.entity.SepettekiYemekler
import com.example.pupilicabitirmeprojesi.databinding.SepetCardTasarimBinding
import com.example.pupilicabitirmeprojesi.ui.viewmodel.SepetViewModel
import com.example.pupilicabitirmeprojesi.utils.resimGoster
import com.google.android.material.snackbar.Snackbar

class SepettekiYemeklerAdapter(var mContext: Context,
                               var sepettekiYemeklerListesi: List<SepettekiYemekler>,
                               var viewModel: SepetViewModel) : RecyclerView.Adapter<SepettekiYemeklerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarimBinding: SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarimBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(
        holder: CardTasarimTutucu,
        position: Int
    ) {
        val sepettekiYemek = sepettekiYemeklerListesi.get(position)
        val t = holder.tasarimBinding
        val f = sepettekiYemek.yemek_fiyat.toString()
        t.yemekSepetAdiCard.text = sepettekiYemek.yemek_adi
        t.fiyatSepetCard.text = "₺${f}"
        t.yemekSepetImageV.resimGoster(sepettekiYemek.yemek_resim_adi)//with Glide
        t.adetSepetCard.text = sepettekiYemek.yemek_siparis_adet.toString()
        t.kullaniciAdiSepetCardText.text = sepettekiYemek.kullanici_adi


        t.silSepetImageV.setOnClickListener {
            Snackbar.make(it,"${sepettekiYemek.yemek_adi} sepetten çıkarılsın mı?", Snackbar.LENGTH_SHORT).setAction("EVET"){
                viewModel.sil(sepettekiYemek.sepet_yemek_id,sepettekiYemek.kullanici_adi)
            }.show()
        }
    }
    fun setData(yeniListe: List<SepettekiYemekler>) {
        sepettekiYemeklerListesi = yeniListe
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return sepettekiYemeklerListesi.size
    }


}