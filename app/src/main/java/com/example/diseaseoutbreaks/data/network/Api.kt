package com.example.diseaseoutbreaks.data.network

import com.example.diseaseoutbreaks.data.Model.DataClass
import com.example.diseaseoutbreaks.data.Model.Item
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("v1/api.json?rss_url=https%3A%2F%2Fwww.who.int%2Ffeeds%2Fentity%2Fcsr%2Fdon%2Fen%2Frss.xml")
    fun getDiseases() : Call<DataClass>
}