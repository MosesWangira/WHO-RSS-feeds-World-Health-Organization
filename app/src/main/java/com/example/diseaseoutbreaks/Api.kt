package com.example.diseaseoutbreaks

import com.example.diseaseoutbreaks.Model.DataClass
import com.example.diseaseoutbreaks.Model.Item
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("api.json?rss_url=https%3A%2F%2Fwww.who.int%2Ffeeds%2Fentity%2Fcsr%2Fdon%2Fen%2Frss.xml")
    fun getDiseases() : Call<List<Item>>

}