package com.example.arayuztasarimodevi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arayuztasarimodevi.databinding.FragmentKonuListeBinding

class KonuListeFragment : Fragment() {
    lateinit var binding: FragmentKonuListeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKonuListeBinding.inflate(inflater, container, false)
        return binding.root
    }

}