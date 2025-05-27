package com.example.pupilicabitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pupilicabitirmeprojesi.databinding.FragmentAnaSayfaBinding
import com.example.pupilicabitirmeprojesi.ui.adapter.YemeklerAdapter
import com.example.pupilicabitirmeprojesi.ui.viewmodel.AnaSayfaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnaSayfaBinding
    private lateinit var viewModel: AnaSayfaViewModel
    private lateinit var yemeklerAdapter: YemeklerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)

        yemeklerAdapter = YemeklerAdapter(requireContext(), listOf(), viewModel)
        binding.yemekRV.adapter = yemeklerAdapter
        binding.yemekRV.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            yemeklerAdapter.updateList(it)
        }

        // Search
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.ara(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.ara(it) }
                return true
            }
        })

        // Filtre
        binding.btnTumu.setOnClickListener { viewModel.filtreleKategoriyeGore("Tümü") }
        binding.btnYemek.setOnClickListener { viewModel.filtreleKategoriyeGore("Yemek") }
        binding.btnIcecek.setOnClickListener { viewModel.filtreleKategoriyeGore("İçecek") }
        binding.btnTatli.setOnClickListener { viewModel.filtreleKategoriyeGore("Tatlı") }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnaSayfaViewModel by viewModels()
        viewModel = tempViewModel
        viewModel.yemekleriYukle()
    }
}
