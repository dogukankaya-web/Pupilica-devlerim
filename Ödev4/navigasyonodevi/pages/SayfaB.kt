package com.example.navigasyonodevi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigasyonodevi.R
import com.example.navigasyonodevi.databinding.FragmentSayfaBBinding


class SayfaB : Fragment() {

    private lateinit var binding: FragmentSayfaBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSayfaBBinding.inflate(inflater, container, false)

        binding.buttonBToY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sayfaB_to_sayfaY)
        }

        return binding.root
    }


}