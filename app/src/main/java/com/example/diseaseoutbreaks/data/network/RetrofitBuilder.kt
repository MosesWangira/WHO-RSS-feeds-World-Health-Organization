package com.example.diseaseoutbreaks.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BASE_URL: String = "https://api.rss2json.com/"


//
//  specifies a cache of 5MB.
//    val cacheSize = (5 * 1024 * 1024).toLong()
//    val myCache = Cache(context.cacheDir, cacheSize)
//
//    val okHttpClient = OkHttpClient.Builder()
//        .cache(myCache)
//        .addInterceptor { chain ->
//            var request = chain.request()
//            request = if (hasNetwork(context)!!)
//                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//            else
//                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//            chain.proceed(request)
//        }
//        .build()

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
    }


    val apiService: Api by lazy{
        retrofitBuilder
            .build()
            .create(Api::class.java)
    }

//    fun hasNetwork(context: Context): Boolean? {
//        var isConnected: Boolean? = false // Initial Value
//        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//        if (activeNetwork != null && activeNetwork.isConnected)
//            isConnected = true
//        return isConnected
//    }
}