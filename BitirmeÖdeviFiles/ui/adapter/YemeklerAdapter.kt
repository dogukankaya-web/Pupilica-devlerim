package com.example.pupilicabitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pupilicabitirmeprojesi.data.entity.Yemekler
import com.example.pupilicabitirmeprojesi.databinding.YemekCardTasarimBinding
import com.example.pupilicabitirmeprojesi.ui.fragment.AnaSayfaFragmentDirections
import com.example.pupilicabitirmeprojesi.ui.viewmodel.AnaSayfaViewModel
import com.example.pupilicabitirmeprojesi.utils.gecisYap
import com.example.pupilicabitirmeprojesi.utils.resimGoster

class YemeklerAdapter(var mContext: Context,
                      var yemeklerListesi: List<Yemekler>,
                      var viewModel: AnaSayfaViewModel,): RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarimBinding: YemekCardTasarimBinding): RecyclerView.ViewHolder(tasarimBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = YemekCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(
        holder: CardTasarimTutucu,
        position: Int
    ) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarimBinding
        val f = yemek.yemek_fiyat.toString()
        t.yemekAdi.text = yemek.yemek_adi
        t.yemekFiyat.text = "₺$f"
        t.yemekImageV.resimGoster(yemek.yemek_resim_adi)//with Glide


        t.sepeteEkle.setOnClickListener  {
            val gecis = AnaSayfaFragmentDirections.anaToDetay(yemek = yemek)
            Navigation.gecisYap(it,gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
    fun updateList(newList: List<Yemekler>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = yemeklerListesi.size
            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return yemeklerListesi[oldItemPosition].yemek_id == newList[newItemPosition].yemek_id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return yemeklerListesi[oldItemPosition] == newList[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        yemeklerListesi = newList
        diffResult.dispatchUpdatesTo(this)
    }

}