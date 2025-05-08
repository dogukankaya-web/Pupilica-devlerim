package com.example.navigasyonodevi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigasyonodevi.R
import com.example.navigasyonodevi.databinding.FragmentSayfaABinding


class SayfaA : Fragment() {

    private lateinit var binding: FragmentSayfaABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSayfaABinding.inflate(inflater, container, false)

        binding.buttonAToB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sayfaA_to_sayfaB)
        }

        return binding.root
    }


}