package com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl

import com.example.weatherappsample1yt.data.api.server.ApiServiceAMS
import com.example.weatherappsample1yt.data.model.aiMeteoSource.currentResponse.toCurrentWeatherData
import com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse.toForecastWeatherData
import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.repository.weather.dataSource.WeatherRemoteDataSource
import com.example.weatherappsample1yt.domain.repository.BaseRepository
import com.example.weatherappsample1yt.domain.repository.getRetrofitService
import okhttp3.OkHttpClient

private class AMSWeatherRemoteDataSourceImpl(
    private val client: OkHttpClient
) : WeatherRemoteDataSource, BaseRepository {
    val baseUrl = "https://ai-weather-by-meteosource.p.rapidapi.com/"
    private val service by lazy {
        return@lazy getRetrofitService(ApiServiceAMS::class.java, baseUrl, client)
    }

    override suspend fun getCurrentWeather(
        lat: Double, lon: Double, units: String
    ): CurrentWeatherData {
        return service?.getCurrentWeatherData(lat, lon, "auto", "en", units)?.body()
            ?.toCurrentWeatherData()
            ?: throw Exception("Service error to get current weather data from AMS")
    }

    override suspend fun getForecast(lat: Double, lon: Double, units: String): ForecastWeatherData {
        return service?.getHourlyWeatherData(lat, lon, "auto", "en", units)?.body()
            ?.toForecastWeatherData()
            ?: throw Exception("Service error to get forecast weather data from AMS")
    }
}

fun getAMSWeatherRemoteDataSourceImpl(client: OkHttpClient): WeatherRemoteDataSource {
    return AMSWeatherRemoteDataSourceImpl(client)
}