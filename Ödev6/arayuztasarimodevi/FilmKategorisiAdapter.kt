package com.example.arayuztasarimodevi // Or your appropriate package

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arayuztasarimodevi.databinding.KonuCardTasarimBinding

class FilmKategorisiAdapter(
    private val mContext: Context,
    private val kategorilerList: List<FilmKategorisi>
) : RecyclerView.Adapter<FilmKategorisiAdapter.KategoriViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class KategoriViewHolder(val binding: KonuCardTasarimBinding) : // Ensure binding class name matches your XML
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val binding = KonuCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return KategoriViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val kategori = kategorilerList[position]
        val t = holder.binding

        t.textViewKategoriBasligi.text = kategori.kategoriBasligi

        val childLayoutManager = LinearLayoutManager(
            t.recyclerViewIcerikFilmler.context, // Use context from the inner RecyclerView
            LinearLayoutManager.HORIZONTAL,
            false
        )

        t.recyclerViewIcerikFilmler.apply {
            layoutManager = childLayoutManager
            adapter = FilmDikeyAdapter(mContext, kategori.filmlerListesi) // Pass the list of films for this category
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return kategorilerList.size
    }
}