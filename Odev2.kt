package com.example.kotlinders.odev

class Odev2 {

    fun soru1(derece: Double): Double {
        val fahrenhiet = derece*1.8 +32
        return fahrenhiet
    }

    fun soru2(kucukKenar: Int,buyukKenar: Int){
        val cevre = kucukKenar*2 + buyukKenar*2
        println("Dikdörtgenin çevresi $cevre cm'dir")
    }

    fun soru3(sayi: Int){
        var faktoriyal = 0
        for (i in 1..sayi){
            faktoriyal *= i
        }
        println("$sayi sayının faktöriyali = $faktoriyal")
    }

    fun soru4(kelime: String){
        val aSayisi = kelime.lowercase().count { it == 'a' }
        println("$kelime kelimesi içinde $aSayisi tane a harfi vardır.")
    }

    fun soru5(kenarSayisi: Int){
        val aciToplami = (kenarSayisi-2)*180
        println("$kenarSayisi kenarlı şeklin iç açıların toplamı = $aciToplami")
    }

    fun soru6(calisilanGun: Int){
        var calisilanSaat = calisilanGun*8
        if (calisilanSaat <= 160){
            var maas = calisilanSaat*10
            println("Bu ayki maaşınız = $maas TL")
        }
        else{
            var maas = (calisilanSaat*10) + ((calisilanSaat-160)*10)
            println("Bu ayki maaşınız = $maas TL")
        }
    }

    fun soru7(harcananGB: Int){
        if (harcananGB <=50){
            println("İnternet faturanız 100 TL dir.")
        }
        else{
            val asimHaliUcreti = 100+((harcananGB-50)*4)
            println("İnternet faturanız $asimHaliUcreti TL dir.")
        }
    }
}