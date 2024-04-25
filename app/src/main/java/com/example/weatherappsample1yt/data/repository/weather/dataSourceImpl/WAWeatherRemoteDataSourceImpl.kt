package com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl

import com.example.weatherappsample1yt.data.api.server.ApiServiceWA
import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.model.weatherAPI.toForecastWeatherData
import com.example.weatherappsample1yt.data.repository.weather.dataSource.WeatherRemoteDataSource
import com.example.weatherappsample1yt.domain.repository.BaseRepository
import com.example.weatherappsample1yt.domain.repository.getRetrofitService
import okhttp3.OkHttpClient

private class WAWeatherRemoteDataSourceImpl(
    private val client: OkHttpClient
) : WeatherRemoteDataSource, BaseRepository {
    private val baseUrl = "https://weatherapi-com.p.rapidapi.com/"
    private val service by lazy {
        return@lazy getRetrofitService(ApiServiceWA::class.java, baseUrl, client)
    }

    override suspend fun getCurrentWeather(
        lat: Double, lon: Double, units: String
    ): CurrentWeatherData? {
        val location = "$lat,$lon"
        val response = service?.getCurrentWeatherData(location)
        return response?.toCurrentWeatherData()
    }

    override suspend fun getForecast(
        lat: Double, lon: Double, units: String
    ): ForecastWeatherData? {
        val location = "$lat,$lon"
        val response = service?.getForecastWeatherData(location, 3)
        return response?.toForecastWeatherData()
    }
}

fun getWAWeatherRemoteDataSourceImpl(client: OkHttpClient): WeatherRemoteDataSource {
    return WAWeatherRemoteDataSourceImpl(client)
}