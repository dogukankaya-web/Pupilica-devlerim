package com.example.todolistodevi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todolistodevi.R
import com.example.todolistodevi.databinding.FragmentAnaSayfaBinding
import com.example.todolistodevi.databinding.FragmentYeniKayitBinding
import com.example.todolistodevi.ui.viewmodel.YeniKayitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YeniKayitFragment : Fragment() {
    private lateinit var binding: FragmentYeniKayitBinding
    private lateinit var  viewModel: YeniKayitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYeniKayitBinding.inflate(inflater, container, false)

        binding.buttonKaydet.setOnClickListener {
            val adi = binding.editTextBaslik.text.toString()
            val aciklama = binding.editTextAciklama.text.toString()
            viewModel.kaydet(adi,aciklama)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YeniKayitViewModel by viewModels()
        viewModel = tempViewModel
    }

}