package com.example.diseaseoutbreaks.Model

import com.example.diseaseoutbreaks.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BASE_URL: String = "https://api.rss2json.com/v1/"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService: Api by lazy{
        retrofitBuilder
            .build()
            .create(Api::class.java)
    }
}