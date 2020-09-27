package com.example.diseaseoutbreaks.data.network

import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("v1/api.json?rss_url=https%3A%2F%2Fwww.who.int%2Ffeeds%2Fentity%2Fcsr%2Fdon%2Fen%2Frss.xml")
    fun getDiseases(): Call<DiseaseDataClass>

    @GET("v1/api.json?rss_url=https%3A%2F%2Fwww.who.int%2Frss-feeds%2Fnews-english.xml")
    fun getNews(): Call<NewsDataClass>

    @GET("v1/api.json?rss_url=https%3A%2F%2Fwww.who.int%2Ffeeds%2Fentity%2Fpmnch%2Fen%2Frss.xml")
    fun getMaternalInformation(): Call<MaternalDataClass>
}