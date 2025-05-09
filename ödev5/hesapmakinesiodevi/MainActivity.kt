package com.example.hesapmakinesiodevi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hesapmakinesiodevi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var ilkNo: String = ""
    private var ikinciNo: String = ""
    private var toplamaDurumu = false
    private var toplam = 0
    private var hesaplamaYapildi = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editTextNumber.setText("0")

        val numberButtonClickListener = View.OnClickListener { view ->
            if (view is Button) {
                val buttonText = view.text.toString()
                if (hesaplamaYapildi) {
                    clear()
                    ilkNo += buttonText
                    binding.editTextNumber.setText(ilkNo)
                } else if (!toplamaDurumu) {
                    ilkNo += buttonText
                    binding.editTextNumber.setText(ilkNo)
                } else {
                    ikinciNo += buttonText
                    binding.editTextNumber.setText("$ilkNo + $ikinciNo")
                }
                hesaplamaYapildi = false
            }
        }
        binding.buttonNo1.setOnClickListener(numberButtonClickListener)
        binding.buttonNo2.setOnClickListener(numberButtonClickListener)
        binding.buttonNo3.setOnClickListener(numberButtonClickListener)
        binding.buttonNo4.setOnClickListener(numberButtonClickListener)
        binding.buttonNo5.setOnClickListener(numberButtonClickListener)
        binding.buttonNo6.setOnClickListener(numberButtonClickListener)
        binding.buttonNo7.setOnClickListener(numberButtonClickListener)
        binding.buttonNo8.setOnClickListener(numberButtonClickListener)
        binding.buttonNo9.setOnClickListener(numberButtonClickListener)
        binding.buttonNo0.setOnClickListener(numberButtonClickListener)


        binding.buttonPlus.setOnClickListener {
        if (hesaplamaYapildi) {
             toplamaDurumu = true
             binding.editTextNumber.setText("$ilkNo +")
        } else if (!toplamaDurumu) {
            if (ilkNo.isNotEmpty()) {
                toplamaDurumu = true
                binding.editTextNumber.setText("$ilkNo +")
            } else {
                Toast.makeText(this, "Önce toplanacak sayıyı giriniz.", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (ikinciNo.isNotEmpty()) {
                calculate()
                binding.editTextNumber.setText("$toplam +")
                ilkNo = toplam.toString()
                ikinciNo = ""
            } else {
                 Toast.makeText(this, "İkinci sayıyı giriniz.", Toast.LENGTH_SHORT).show()
            }
        }
         hesaplamaYapildi = false
        }
        binding.buttonSonucla.setOnClickListener {
            if (ilkNo.isNotEmpty()) {
                calculate()
                binding.editTextNumber.setText("$toplam")

                hesaplamaYapildi = true
                ilkNo = toplam.toString()
                ikinciNo = ""
                toplamaDurumu = false
            }

            else {
                Toast.makeText(this, "Hesaplama için eksik bilgi.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonClear.setOnClickListener {
            clear()
        }
    }
    fun clear(){
        ilkNo = ""
        ikinciNo = ""
        toplam = 0
        binding.editTextNumber.setText("0")
        toplamaDurumu = false
    }
    fun calculate(){
        var ilkNoInt = ilkNo.toInt()
        var ikinciNoInt = ikinciNo.toIntOrNull()
        if (ikinciNoInt!=null){
            toplam = ilkNoInt + ikinciNoInt
        }
        else{
            toplam = ilkNoInt
        }
        toplam.toString()
    }
}