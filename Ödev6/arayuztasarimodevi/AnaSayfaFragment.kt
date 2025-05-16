package com.example.arayuztasarimodevi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.arayuztasarimodevi.databinding.FragmentAnaSayfaBinding


class AnaSayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnaSayfaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)

        val filmlerListesi = ArrayList<Filmler>()
        val f1 = Filmler(1,"tales_of_the_jedi_logo")
        val f2 = Filmler(2,"boba_fett")
        val f3 = Filmler(3,"falcon_wintersoldier_yatay")
        val f4 = Filmler(4,"wandavision_card")
        val f5 = Filmler(5,"the_mandalorian_yatay")
        val f6 = Filmler(6,"loki_yatay")
        filmlerListesi.add(f1)
        filmlerListesi.add(f2)
        filmlerListesi.add(f3)
        filmlerListesi.add(f4)
        filmlerListesi.add(f5)
        filmlerListesi.add(f6)


        val izlemeyeDevamList = ArrayList<FilmDikey>()
        izlemeyeDevamList.add(FilmDikey(1,"mandalorian_dikey"))
        izlemeyeDevamList.add(FilmDikey(2,"goosebumps_dikey"))
        izlemeyeDevamList.add(FilmDikey(3,"goonies_afis_dikey"))
        izlemeyeDevamList.add(FilmDikey(7,"whatif_dikey"))

        val seninIcinOnerilerList = ArrayList<FilmDikey>()
        seninIcinOnerilerList.add(FilmDikey(4,"real_steel_dikey"))
        seninIcinOnerilerList.add(FilmDikey(5,"zootropolis_dikey"))
        seninIcinOnerilerList.add(FilmDikey(6,"guzel_ve_cirkin_dikey"))
        seninIcinOnerilerList.add(FilmDikey(8,"andor_dikey"))

        val tumKategorilerListesi = ArrayList<FilmKategorisi>()
        tumKategorilerListesi.add(
            FilmKategorisi(
                kategoriBasligi = "İzlemeye Devam Et",
                filmlerListesi = izlemeyeDevamList
            )
        )
        tumKategorilerListesi.add(
            FilmKategorisi(
                kategoriBasligi = "Senin İçin Öneriler",
                filmlerListesi = seninIcinOnerilerList
            )
        )


        val filmlerAdapter = FilmlerAdapter(requireContext(),filmlerListesi)
        binding.mainListrv.adapter = filmlerAdapter

        binding.mainListrv.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        val outerAdapter = FilmKategorisiAdapter(requireContext(), tumKategorilerListesi)

        binding.konularrv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL, false)

            adapter = outerAdapter
        }

        return binding.root
    }

}