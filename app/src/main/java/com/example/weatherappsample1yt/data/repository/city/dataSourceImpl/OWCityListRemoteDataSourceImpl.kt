package com.example.weatherappsample1yt.data.repository.city.dataSourceImpl

import com.example.weatherappsample1yt.data.api.client.ApiKeyProvider
import com.example.weatherappsample1yt.data.api.client.ApiProvider
import com.example.weatherappsample1yt.data.api.server.ApiServiceOW
import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.data.model.openWeather.toCityWeatherData
import com.example.weatherappsample1yt.data.repository.city.dataSource.CityListRemoteDataSource
import com.example.weatherappsample1yt.domain.repository.BaseRepository
import com.example.weatherappsample1yt.domain.repository.getRetrofitService
import okhttp3.OkHttpClient

private class OWCityListRemoteDataSourceImpl(
    client: OkHttpClient
) : CityListRemoteDataSource, BaseRepository {
    private val service by lazy {
        return@lazy getRetrofitService(
            ApiServiceOW::class.java,
            "https://api.openweathermap.org/",
            client
        )
    }

    override suspend fun getCities(cityName: String, limit: Int): CityWeatherData? {
        val response = service?.getCitiesList(
            cityName,
            limit,
            ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER)
        )
        return response?.toCityWeatherData()
    }
}

fun getOWCityListRemoteDataSourceImpl(client: OkHttpClient): CityListRemoteDataSource {
    return OWCityListRemoteDataSourceImpl(client)
}