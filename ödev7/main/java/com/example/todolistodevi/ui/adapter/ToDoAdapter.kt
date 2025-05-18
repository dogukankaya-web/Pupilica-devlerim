package com.example.todolistodevi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistodevi.data.entity.ToDoList
import com.example.todolistodevi.databinding.CardTasarimBinding
import com.example.todolistodevi.ui.fragment.AnaSayfaFragment
import com.example.todolistodevi.ui.fragment.AnaSayfaFragmentDirections
import com.example.todolistodevi.ui.viewmodel.AnaSayfaViewModel
import com.example.todolistodevi.utils.gecisYap
import com.google.android.material.snackbar.Snackbar

class ToDoAdapter(var mContext: Context,var toDoList: List<ToDoList>,var viewModel: AnaSayfaViewModel) :
    RecyclerView.Adapter<ToDoAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim: CardTasarimBinding): RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardTasarimTutucu {
        val tasarim = CardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(
        holder: CardTasarimTutucu,
        position: Int
    ) {
        val oge = toDoList.get(position)
        val t = holder.tasarim
        t.textViewAdi.text = oge.adi
        t.textViewAciklama.text = oge.aciklama

        t.cardViewSatir.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.detayGecis(oge = oge)
            Navigation.gecisYap(it,gecis)
        }

        t.imageViewSil.setOnClickListener {
            viewModel.sil(oge.id)
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}