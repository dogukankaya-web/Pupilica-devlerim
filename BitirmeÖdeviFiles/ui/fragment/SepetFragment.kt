package com.example.pupilicabitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pupilicabitirmeprojesi.R
import com.example.pupilicabitirmeprojesi.data.entity.GecmisYemekler
import com.example.pupilicabitirmeprojesi.databinding.FragmentSepetBinding
import com.example.pupilicabitirmeprojesi.ui.adapter.SepettekiYemeklerAdapter
import com.example.pupilicabitirmeprojesi.ui.viewmodel.AktifKullaniciViewModel
import com.example.pupilicabitirmeprojesi.ui.viewmodel.GecmisViewModel
import com.example.pupilicabitirmeprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {

    private lateinit var binding: FragmentSepetBinding
    private val sepetViewModel: SepetViewModel by viewModels()
    private val aktifKullaniciViewModel: AktifKullaniciViewModel by activityViewModels()
    private val gecmisViewModel: GecmisViewModel by activityViewModels()
    private lateinit var sepetAdapter: SepettekiYemeklerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSepetBinding.inflate(inflater, container, false)
        Log.d("SepetFragmentLog", "onCreateView çağrıldı")

        sepetAdapter = SepettekiYemeklerAdapter(requireContext(), emptyList(), sepetViewModel)
        binding.sepetRV.layoutManager = LinearLayoutManager(requireContext())
        binding.sepetRV.adapter = sepetAdapter

        binding.buttonSiparis.setOnClickListener { view ->
            val siparisListesi = sepetViewModel.sepettekiYemeklerListesi.value
            val aktifKullanici = aktifKullaniciViewModel.aktifKullaniciAdi.value

            if (siparisListesi.isNullOrEmpty()) {
                Log.d("SepetFragmentLog", "Sipariş listesi boş.")
                return@setOnClickListener
            }

            if (aktifKullanici.isNullOrEmpty()) {
                Log.w("SepetFragmentLog", "Aktif kullanıcı adı boş. Sipariş geçmişe eklenemiyor.")
                return@setOnClickListener
            }

            Log.d("SepetFragmentLog", "Sipariş veriliyor: ${siparisListesi.size} ürün, Kullanıcı: $aktifKullanici")

            val gecmisYemekListesi = siparisListesi.map { sepetYemegi ->
                GecmisYemekler(
                    yemek_adi = sepetYemegi.yemek_adi,
                    yemek_resim_adi = sepetYemegi.yemek_resim_adi,
                    yemek_fiyat = (sepetYemegi.yemek_fiyat.toIntOrNull() ?: 0),
                    yemek_siparis_adet = (sepetYemegi.yemek_siparis_adet.toIntOrNull() ?: 0),
                    silinecekZaman = System.currentTimeMillis() + (15 * 60 * 1000L), // 15 dakika
                    kullaniciAdi = aktifKullanici
                )
            }

            gecmisViewModel.siparisiEkle(gecmisYemekListesi) // ViewModel'e ekleme
            Log.d("SepetFragmentLog", "${gecmisYemekListesi.size} yemek GecmisViewModel'e eklendi.")

            // Sepeti temizle
            for (yemek in siparisListesi) {
                sepetViewModel.sil(yemek.sepet_yemek_id, aktifKullanici)
            }
            Log.d("SepetFragmentLog", "Sepet temizlendi.")

            try {
                Navigation.findNavController(view).navigate(R.id.sepet_to_gecmis)
                Log.d("SepetFragmentLog", "GecmisFragment'a navigate edildi.")
            } catch (e: IllegalArgumentException) {
                Log.e("SepetFragmentLog", "Navigasyon hatası (sepet_to_Gecmis): Action bulunamadı.", e)
            }
        }

        binding.anaSayfayaDonfab2.setOnClickListener {
            try {
                Navigation.findNavController(it).navigate(R.id.sepet_to_Ana)
                Log.d("SepetFragmentLog", "AnaSayfaFragment'a navigate edildi.")
            } catch (e: IllegalArgumentException) {
                Log.e("SepetFragmentLog", "Navigasyon hatası (sepet_to_Ana): Action bulunamadı.", e)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SepetFragmentLog", "onViewCreated çağrıldı")
        setupObservers()
    }

    private fun setupObservers() {
        aktifKullaniciViewModel.aktifKullaniciAdi.observe(viewLifecycleOwner) { kullaniciAdi ->
            Log.d("SepetFragmentLog", "Aktif kullanıcı observe edildi: $kullaniciAdi")
            if (kullaniciAdi != null) { // Null kontrolü
                sepetViewModel.kullaniciAdiDegistigindeSepetiYukle(kullaniciAdi)
            } else {
                sepetViewModel.kullaniciAdiDegistigindeSepetiYukle("") // veya sepeti temizle
                Log.w("SepetFragmentLog", "Aktif kullanıcı adı null, sepet boş kullanıcı için yüklendi/temizlendi.")
            }
        }

        sepetViewModel.sepettekiYemeklerListesi.observe(viewLifecycleOwner) { yemeklerListesi ->
            val guncelListe = yemeklerListesi ?: emptyList()
            sepetAdapter.setData(guncelListe)

            val toplamFiyat = guncelListe.sumOf {
                (it.yemek_fiyat.toIntOrNull() ?: 0)
            }

            binding.sepetToplamFiyat.text = "₺$toplamFiyat"
            Log.d("SepetFragmentLog", "Sepet listesi observe edildi. Eleman sayısı: ${guncelListe.size}, Toplam Fiyat: $toplamFiyat")
        }
    }
}