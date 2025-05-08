package com.example.navigasyonodevi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigasyonodevi.R
import com.example.navigasyonodevi.databinding.FragmentSayfaXBinding


class SayfaX : Fragment() {

    private lateinit var binding: FragmentSayfaXBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSayfaXBinding.inflate(inflater, container, false)

        binding.buttonXToY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_sayfaX_to_sayfaY)
        }

        return binding.root
    }


}