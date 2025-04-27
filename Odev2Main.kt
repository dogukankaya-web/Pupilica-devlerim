package com.example.kotlinders.odev

fun main() {

    val f = Odev2()

    //soru 1
    val derece = 25.0
    val fahrenhiet = f.soru1(derece)
    println("$derece derece = $fahrenhiet fahrenhiet ")
    println("****************")


    //soru 2
    val dikDortKucukKenar = 5
    val dikDortBuyukKenar = 10
    f.soru2(dikDortKucukKenar,dikDortBuyukKenar)
    println("****************")


    //soru 3
    val sayi = 5
    f.soru3(sayi)
    println("****************")


    //soru 4
    val ad1 = "Ahmet"
    val ad2 = "Hasan"
    f.soru4(ad1)
    f.soru4(ad2)
    println("****************")


    //soru 5
    val pentagonKenarSayi = 5
    val hexagonKenarSayi = 6
    f.soru5(pentagonKenarSayi)
    f.soru5(hexagonKenarSayi)
    println("****************")

    //soru 6
    val calisilanGun1 = 15
    val calisilanGun2 = 28
    f.soru6(calisilanGun1)
    f.soru6(calisilanGun2)
    println("****************")

    //soru 7
    val harcananGB1 = 15
    val harcananGB2 = 60
    f.soru7(harcananGB1)
    f.soru7(harcananGB2)
}