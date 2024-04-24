package com.example.netflixclone.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object objectforapi {
    private const val BASE_URL = "http://172.22.37.171:4000"


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: userapi = retrofit.create(userapi::class.java)

}
