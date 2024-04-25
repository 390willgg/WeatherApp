package com.example.weatherappsample1yt.data.repository.city.dataSourceImpl

import com.example.weatherappsample1yt.data.api.server.ApiServiceAMS
import com.example.weatherappsample1yt.data.model.aiMeteoSource.cityResponse.toCityWeatherData
import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.data.repository.city.dataSource.CityListRemoteDataSource
import com.example.weatherappsample1yt.domain.repository.BaseRepository
import com.example.weatherappsample1yt.domain.repository.getRetrofitService
import okhttp3.OkHttpClient

private class AMSCityListRemoteDataSourceImpl(
    private val client: OkHttpClient
) : CityListRemoteDataSource, BaseRepository {
    val baseUrl = "https://ai-weather-by-meteosource.p.rapidapi.com/"
    private val service by lazy {
        return@lazy getRetrofitService(ApiServiceAMS::class.java, baseUrl, client)
    }

    override suspend fun getCities(cityName: String, limit: Int): CityWeatherData {
        return service?.getCitiesWeatherData(cityName, "en")?.toCityWeatherData()
            ?: throw Exception("Service error to get cities data from AMS")
    }
}

fun getAMSCityListRemoteDataSourceImpl(client: OkHttpClient): CityListRemoteDataSource {
    return AMSCityListRemoteDataSourceImpl(client)
}