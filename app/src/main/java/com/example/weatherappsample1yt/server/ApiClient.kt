package com.example.weatherappsample1yt.server

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var retrofit: Retrofit
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()

            // Tambahkan header jika ada
            val headers = ApiInfoData.apiInfo[ApiProvider.OPEN_WEATHER]?.headers
            headers?.forEach { (key, value) ->
                requestBuilder.addHeader(key, value)
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    fun getClient(apiProvider: ApiProvider): Retrofit {
        val baseUrl = ApiInfoData.apiInfo[apiProvider]?.baseUrl ?: ""

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}