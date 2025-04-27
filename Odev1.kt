package com.example.kotlinders.odev

fun main (){

    /*küstahlık olsun istemem ama her bir öğeyi print yaparken
    tekrar yazmak istemedim o yüzden listeyi yazdıracağım
    */

    val liste = mutableMapOf<String, Any>()

    liste.apply {
        put("isim", "Doğukan")
        put("ülke", "Türkiye")
        put("Telefon", "+90538642")
        put("posta kodu", 35355)
        put("email", "dogukankaya@email")
        put("meslek", "keşke olsa")
        put("stok miktarı", 1000)
        put("müşteri adı", "Ayşe")
        put("bakiye", 500.0)
        put("doğum günü", "2 Mayıs 2000")
        put("maaş", 9999999.0)
        put("medeni durum", "bekar")
        put("ürün yorum", "beğendim *****")
        put("ödeme tarihi", "05.05.2025")
        put("ödeme", false)
        put("spariş adeti", 285)
        put("araba modeli", 2020)
        put("kitap adı", "Zaman Bisikleti")
        put("yayınlanma tarhi", "haftaya")
        put("indirim miktarı", "%50")
        put("oda sayısı", 185)
        put("enlem", 15.0)
        put("boylam", 75.0)
        put("ürün adı", "vazo")
        put("yemek fiyatı", "850TL")
        put("marka", "Alfa Romeo")
        put("müzik adı", "Gurbet")
        put("video süresi", "25 dakika")
        put("ürün puanı", 10.0)
        put("resim adı", "Un Bleuet - Alfred Stevens")
        put("dosya formatı", "PNG")
        put("renk", "mavi")
        put("renk kodu", "#FF7F00")
        put("telefon modeli", "GalaxyA55")
        put("ekran boyutu", 15.9)
        put("ağırlık", 1.5)
        put("ulusal gün", "23 Nisan")
        put("tatil günü", "23 Nisan")
        put("rezervasyon tarihi", "8 Aralık 2025")
        put("sokak adı", "Sehit Fetih Sekin Sokağı")
        put("otobüs hattı", 568)
        put("kalan dakika", 25)
        put("takip kodu", "D8986258")
        put("kupon süresi", "3 - 8 Nisan 2025")
        put("kupon kodu", "KA38NISAN2025")
        put("fatura adresi", "Osmangazi,İzmir")

    }
    println(liste.entries.joinToString { //Listedeki herşeyi yan yana yazsın diye
        (key, value) -> "$key = $value" })
}