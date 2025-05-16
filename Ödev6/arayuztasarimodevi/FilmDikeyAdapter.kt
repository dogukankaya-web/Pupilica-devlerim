package com.example.arayuztasarimodevi // Or your appropriate package

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arayuztasarimodevi.databinding.KonuFilmCardTasarimBinding


class FilmDikeyAdapter(
    private val mContext: Context,
    private val filmlerList: List<FilmDikey>
) : RecyclerView.Adapter<FilmDikeyAdapter.FilmDikeyViewHolder>() {

    inner class FilmDikeyViewHolder(val binding: KonuFilmCardTasarimBinding) : // Ensure binding class name matches your XML
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmDikeyViewHolder {
        val binding = KonuFilmCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return FilmDikeyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmDikeyViewHolder, position: Int) {
        val film = filmlerList[position]
        val t = holder.binding

        val imageResId = mContext.resources.getIdentifier(
            film.resim,
            "drawable",
            mContext.packageName
        )

        if (imageResId != 0) {
            t.imageViewFilmDikeyPoster.setImageResource(imageResId)
        }
    }

    override fun getItemCount(): Int {
        return filmlerList.size
    }
}