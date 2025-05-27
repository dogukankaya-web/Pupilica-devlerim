package com.example.pupilicabitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.pupilicabitirmeprojesi.R
import com.example.pupilicabitirmeprojesi.data.entity.Yemekler
import com.example.pupilicabitirmeprojesi.databinding.FragmentDetayBinding
import com.example.pupilicabitirmeprojesi.ui.viewmodel.AktifKullaniciViewModel
import com.example.pupilicabitirmeprojesi.ui.viewmodel.DetayViewModel
import com.example.pupilicabitirmeprojesi.utils.gecisYap
import com.example.pupilicabitirmeprojesi.utils.resimGoster
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: DetayViewModel by viewModels()
    private val aktifKullaniciViewModel: AktifKullaniciViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        val bundle : DetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        var adet = 1
        var toplamFiyat = gelenYemek.yemek_fiyat * adet

        binding.anaSayfayaDonfab.setOnClickListener {
            Navigation.gecisYap(it,R.id.detay_to_ana)
        }

        binding.adetText.text = adet.toString()
        binding.fiyatToplamText.text = "₺$toplamFiyat"
        binding.yemekAdiDetay.text = gelenYemek.yemek_adi
        binding.yemekDetayImageV.resimGoster(gelenYemek.yemek_resim_adi)

        binding.adetArttR.setOnClickListener {
            adet += 1
            toplamFiyat = gelenYemek.yemek_fiyat * adet
            binding.adetText.text = adet.toString()
            binding.fiyatToplamText.text = "₺$toplamFiyat"
        }

        binding.adetAzalt.setOnClickListener {
            if (adet > 1) {
                adet -= 1
                toplamFiyat = gelenYemek.yemek_fiyat * adet
                binding.adetText.text = adet.toString()
                binding.fiyatToplamText.text = "₺$toplamFiyat"
            } else {
                Toast.makeText(context, "Adet 1'den az olamaz", Toast.LENGTH_SHORT).show()
            }
        }
        binding.sepeteEkle.setOnClickListener {
            val yemek_adi = gelenYemek.yemek_adi
            val yemek_fiyat = toplamFiyat
            val yemek_resim = gelenYemek.yemek_resim_adi
            val yemek_adet = adet
            val kullanici_adi = binding.editTextText.text.toString()

            if (kullanici_adi.trim().isNotEmpty()) {
                aktifKullaniciViewModel.setAktifKullaniciAdi(kullanici_adi)
                viewModel.sepeteEkle(
                    gelenYemek.yemek_id,
                    yemek_adi,
                    yemek_resim,
                    yemek_fiyat,
                    yemek_adet,
                    kullanici_adi
                ){
                    view?.let {
                        Navigation.gecisYap(it, R.id.detay_to_sepet)
                    }
                }
            } else {
                Toast.makeText(context, "Adres sahibini yazınız", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}