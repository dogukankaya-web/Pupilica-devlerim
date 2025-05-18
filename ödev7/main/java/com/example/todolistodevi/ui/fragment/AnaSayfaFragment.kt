package com.example.todolistodevi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistodevi.R
import com.example.todolistodevi.databinding.FragmentAnaSayfaBinding
import com.example.todolistodevi.ui.adapter.ToDoAdapter
import com.example.todolistodevi.ui.viewmodel.AnaSayfaViewModel
import com.example.todolistodevi.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnaSayfaBinding
    private lateinit var viewModel: AnaSayfaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            Navigation.gecisYap(it,R.id.kayitGecis)
        }
        viewModel.toDoList.observe(viewLifecycleOwner) {
            val toDoAdapter = ToDoAdapter(requireContext(),it,viewModel)
            binding.listrv.adapter = toDoAdapter
        }

        binding.listrv.layoutManager = LinearLayoutManager(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.ara(query)
                return true
            }
        })
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnaSayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.listeYukle()
    }

}