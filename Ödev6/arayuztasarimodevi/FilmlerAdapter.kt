package com.example.arayuztasarimodevi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.arayuztasarimodevi.databinding.FilmCardTasarimBinding
import com.google.android.material.snackbar.Snackbar

class FilmlerAdapter(var mContext: Context,var filmlerListesi: List<Filmler>)
    : RecyclerView.Adapter<FilmlerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim: FilmCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        var binding = FilmCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val film = filmlerListesi.get(position)
        val t = holder.tasarim
        t.filmYatayiv.setImageResource(
            mContext.resources.getIdentifier(film.resim,"drawable",mContext.packageName)
        )
    }

    override fun getItemCount(): Int {
        return filmlerListesi.size
    }

}