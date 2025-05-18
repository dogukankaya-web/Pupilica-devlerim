package com.example.todolistodevi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todolistodevi.R
import com.example.todolistodevi.databinding.FragmentDetayBinding
import com.example.todolistodevi.ui.viewmodel.DetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: DetayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)

        val bundle: DetayFragmentArgs by navArgs()
        val yeniOge = bundle.oge

        binding.editTextBaslik.setText(yeniOge.adi)
        binding.editTextAciklama.setText(yeniOge.aciklama)

        binding.buttonGuncelle.setOnClickListener {
            val adi = binding.editTextBaslik.text.toString()
            val aciklama = binding.editTextAciklama.text.toString()
            viewModel.guncelle(yeniOge.id,adi,aciklama)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel
    }

}