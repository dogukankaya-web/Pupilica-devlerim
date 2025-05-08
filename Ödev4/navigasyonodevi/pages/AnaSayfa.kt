package com.example.navigasyonodevi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigasyonodevi.R
import com.example.navigasyonodevi.databinding.FragmentAnaSayfaBinding


class AnaSayfa : Fragment() {

    private lateinit var binding: FragmentAnaSayfaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)

        binding.buttonAnaToA.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_anaSayfa_to_sayfaA)
        }
        binding.buttonAnaToX.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_anaSayfa_to_sayfaX)
        }

        return binding.root
    }

}