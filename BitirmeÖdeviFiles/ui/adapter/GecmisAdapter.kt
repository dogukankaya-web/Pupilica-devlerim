package com.example.pupilicabitirmeprojesi.ui.adapter

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pupilicabitirmeprojesi.data.entity.GecmisYemekler
import com.example.pupilicabitirmeprojesi.databinding.GecmisCardTasarimBinding

class GecmisAdapter(
    private val context: Context,
    private var gecmisListesi: List<GecmisYemekler>,
    private var aktifKullaniciAdiLocal: String,
    private val onSiparisIptal: (GecmisYemekler) -> Unit
) : RecyclerView.Adapter<GecmisAdapter.GecmisViewHolder>() {

    inner class GecmisViewHolder(val binding: GecmisCardTasarimBinding) : RecyclerView.ViewHolder(binding.root) {
        var countDownTimer: CountDownTimer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GecmisViewHolder {
        val binding = GecmisCardTasarimBinding.inflate(LayoutInflater.from(context), parent, false)
        return GecmisViewHolder(binding)
    }

    override fun getItemCount(): Int = gecmisListesi.size

    override fun onBindViewHolder(holder: GecmisViewHolder, position: Int) {
        val yemek = gecmisListesi[position]
        val binding = holder.binding

        binding.yemekAdiText.text = yemek.yemek_adi
        binding.adetText.text = "Adet: ${yemek.yemek_siparis_adet}"
        binding.fiyatText.text = "Fiyat: ₺${yemek.yemek_fiyat}"
        binding.kullaniciAdiText.text = "${yemek.kullaniciAdi}"

        binding.lottieAnimasyon.playAnimation()

        val kalanSureMillis = yemek.silinecekZaman - System.currentTimeMillis()

        holder.countDownTimer?.cancel()

        if (kalanSureMillis > 0) {
            holder.countDownTimer = object : CountDownTimer(kalanSureMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {

                    if (holder.adapterPosition != RecyclerView.NO_POSITION) {

                        onSiparisIptal(gecmisListesi[holder.adapterPosition])
                        Log.d("GecmisAdapter", "${gecmisListesi[holder.adapterPosition].yemek_adi} siparişi süresi doldu, siliniyor.")
                    }
                }
            }.start()
        } else {
            // Süre zaten dolmuşsa hemen iptal et
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onSiparisIptal(yemek)
                Log.d("GecmisAdapter", "${yemek.yemek_adi} siparişi zaten süresi dolmuş, hemen siliniyor.")
            }
        }

        binding.iptalImage.setOnClickListener {
            holder.countDownTimer?.cancel() // Kullanıcı iptal ederse timer'ı da durdur
            binding.lottieAnimasyon.pauseAnimation() // Animasyonu durdur
            // Kullanıcı tarafından iptal edilen sipariş için callback'i çağır
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onSiparisIptal(gecmisListesi[holder.adapterPosition])
                Log.d("GecmisAdapter", "${gecmisListesi[holder.adapterPosition].yemek_adi} siparişi kullanıcı tarafından iptal edildi.")
            }
        }
    }

    override fun onViewRecycled(holder: GecmisViewHolder) {
        super.onViewRecycled(holder)
        // ViewHolder recycle edildiğinde timer'ı kesinlikle iptal et
        holder.countDownTimer?.cancel()
        holder.countDownTimer = null // Referansı temizle
        holder.binding.lottieAnimasyon.cancelAnimation() // Lottie animasyonunu da iptal et/durdur
    }

    fun updateData(yeniListe: List<GecmisYemekler>, kullaniciAdi: String) {
        this.aktifKullaniciAdiLocal = kullaniciAdi // Alınan kullanıcı adını sakla (belki ileride lazım olur)

        this.gecmisListesi = yeniListe

        Log.d("GecmisAdapter", "updateData çağrıldı. Alınan yeni liste boyutu: ${yeniListe.size}, Atanan liste boyutu: ${this.gecmisListesi.size}, Kullanıcı: $kullaniciAdi")
        notifyDataSetChanged()
    }
}