package com.example.weatherappsample1yt.domain.repository

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface BaseRepository

fun <T> BaseRepository.getRetrofitService(serviceClass: Class<T>, baseUrl: String, client: OkHttpClient): T? {
    return try {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(serviceClass)
    } catch (e: Exception) {
        println("Failed to create Retrofit service: ${e.message}")
        Log.e("Base Repository", "Failed to create Retrofit service: ${e.message}")
        null
    }
}