package com.trolle.trolleapp.data.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitClient {
//    private const val BASE_URL = "https://api.github.com/"
    private const val BASE_URL = "http://192.168.100.37:3000/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance: Api = retrofit.create(Api::class.java)

}