package com.example.pupilicabitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
// import androidx.navigation.fragment.navArgs // Kullanılmıyorsa kaldırılabilir
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pupilicabitirmeprojesi.R // Snackbar için string resource
import com.example.pupilicabitirmeprojesi.databinding.FragmentGecmisBinding
import com.example.pupilicabitirmeprojesi.ui.adapter.GecmisAdapter
import com.example.pupilicabitirmeprojesi.ui.viewmodel.AktifKullaniciViewModel
import com.example.pupilicabitirmeprojesi.ui.viewmodel.GecmisViewModel
import com.example.pupilicabitirmeprojesi.data.entity.GecmisYemekler
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GecmisFragment : Fragment() {
    private lateinit var binding: FragmentGecmisBinding
    private val gecmisViewModel: GecmisViewModel by activityViewModels()
    private val aktifKullaniciViewModel: AktifKullaniciViewModel by activityViewModels()
    private lateinit var gecmisAdapter: GecmisAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGecmisBinding.inflate(inflater, container, false)
        Log.d("GecmisFragmentLog", "onCreateView çağrıldı")

        gecmisAdapter = GecmisAdapter(
            requireContext(),
            emptyList<GecmisYemekler>(),
            "",
            onSiparisIptal = { yemek -> // Adapter'dan gelen sipariş öğesi
                Log.d("GecmisFragmentLog", "Adapter'dan sipariş iptal isteği alındı: ${yemek.yemek_adi}")
                showDeleteConfirmationSnackbar(yemek)
            }
        )

        binding.recyclerViewGecmis.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gecmisAdapter
        }
        return binding.root
    }

    private fun showDeleteConfirmationSnackbar(yemek: GecmisYemekler) {
        Snackbar.make(binding.root, getString(R.string.gecmis_sil_onay_mesaji, yemek.yemek_adi), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.evet)) {
                Log.d("GecmisFragmentLog", "Snackbar 'Evet' tıklandı. Sipariş siliniyor: ${yemek.yemek_adi}")
                gecmisViewModel.siparisIptal(yemek)
                // Silme işleminden sonra UI'ın güncellenmesi gecmisList observer tarafından tetiklenmeli.
            }
            .show()
        Log.d("GecmisFragmentLog", "Silme onayı Snackbar gösterildi: ${yemek.yemek_adi}")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("GecmisFragmentLog", "onViewCreated çağrıldı")

        var mevcutAktifKullanici = aktifKullaniciViewModel.aktifKullaniciAdi.value ?: ""

        aktifKullaniciViewModel.aktifKullaniciAdi.observe(viewLifecycleOwner) { kullaniciAdi ->
            val yeniKullanici = kullaniciAdi ?: ""
            Log.d("GecmisFragmentLog", "Aktif Kullanıcı Observer. Yeni: '$yeniKullanici', Önceki: '$mevcutAktifKullanici'")
            if (mevcutAktifKullanici != yeniKullanici) {
                mevcutAktifKullanici = yeniKullanici
                val guncelGecmisListesi = gecmisViewModel.gecmisList.value ?: emptyList()
                Log.d("GecmisFragmentLog", "Aktif kullanıcı değişti. Adapter güncelleniyor. Kullanıcı: '$mevcutAktifKullanici'")
                gecmisAdapter.updateData(guncelGecmisListesi, mevcutAktifKullanici)
                updateEmptyViewVisibility()
            }
        }

        gecmisViewModel.gecmisList.observe(viewLifecycleOwner) { liste ->
            val guncelListe = liste ?: emptyList()
            val anlikAktifKullanici = aktifKullaniciViewModel.aktifKullaniciAdi.value ?: ""
            Log.d("GecmisFragmentLog", "GecmisList Observer. Liste Boyutu: ${guncelListe.size}. Kullanıcı: '$anlikAktifKullanici'")
            gecmisAdapter.updateData(guncelListe, anlikAktifKullanici)
            updateEmptyViewVisibility()
        }

        binding.anaSayfayaDonfab3.setOnClickListener {
            try {
                val action = GecmisFragmentDirections.gecmisToAna()
                Navigation.findNavController(it).navigate(action)
            } catch (e: Exception) {
                Log.e("GecmisFragmentLog", "Navigasyon hatası (anaSayfayaDonfab3): ${e.message}", e)
            }
        }
        Log.d("GecmisFragmentLog", "onViewCreated tamamlandı. Başlangıçtaki aktif kullanıcı: '$mevcutAktifKullanici'")
        updateEmptyViewVisibility()
    }

    private fun updateEmptyViewVisibility() {
        if (gecmisAdapter.itemCount == 0) {
            binding.textViewGecmisBos.visibility = View.VISIBLE
            binding.recyclerViewGecmis.visibility = View.GONE
            Log.d("GecmisFragmentLog", "Geçmiş (filtrelenmiş) boş. Mesaj gösteriliyor.")
        } else {
            binding.textViewGecmisBos.visibility = View.GONE
            binding.recyclerViewGecmis.visibility = View.VISIBLE
            Log.d("GecmisFragmentLog", "Geçmiş (filtrelenmiş) dolu. RecyclerView gösteriliyor.")
        }
    }
}