package com.example.weatherappsample1yt.data.repository.city

import com.example.weatherappsample1yt.data.api.client.ApiKeyProvider
import com.example.weatherappsample1yt.data.api.client.ApiProvider
import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.data.repository.city.dataSource.CityListRemoteDataSource
import com.example.weatherappsample1yt.data.repository.city.dataSourceImpl.getWACityListRemoteDataSourceImpl
import com.example.weatherappsample1yt.domain.repository.CityRepository
import okhttp3.OkHttpClient

private class WACityListRepositoryImpl: CityRepository {
    private val apiKey = ApiKeyProvider.getApiKey(ApiProvider.WEATHER_API)
    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.addHeader("X-RapidAPI-Key", apiKey)
        requestBuilder.addHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val cityListRemoteDataSource: CityListRemoteDataSource = getWACityListRemoteDataSourceImpl(client)
    override suspend fun getCitiesList(cityName: String, limit: Int): CityWeatherData? {
        return cityListRemoteDataSource.getCities(cityName, limit)
    }
}

fun getWACityListRepositoryImpl(): CityRepository {
    return WACityListRepositoryImpl()
}