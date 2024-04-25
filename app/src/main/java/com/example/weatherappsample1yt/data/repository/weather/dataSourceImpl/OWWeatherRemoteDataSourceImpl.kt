package com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl

import com.example.weatherappsample1yt.data.api.client.ApiKeyProvider
import com.example.weatherappsample1yt.data.api.client.ApiProvider
import com.example.weatherappsample1yt.data.api.server.ApiServiceOW
import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.model.openWeather.currentResponse.toCurrentWeatherData
import com.example.weatherappsample1yt.data.model.openWeather.forecastResponse.toForecastWeatherData
import com.example.weatherappsample1yt.data.repository.weather.dataSource.WeatherRemoteDataSource
import com.example.weatherappsample1yt.domain.repository.BaseRepository
import com.example.weatherappsample1yt.domain.repository.getRetrofitService
import okhttp3.OkHttpClient

private class OWWeatherRemoteDataSourceImpl(
    client: OkHttpClient
) : WeatherRemoteDataSource, BaseRepository {
    private val service by lazy {
        return@lazy getRetrofitService(
            ApiServiceOW::class.java, "https://api.openweathermap.org/", client
        )

    }

    override suspend fun getCurrentWeather(
        lat: Double, lon: Double, units: String
    ): CurrentWeatherData? {
        val response = service?.getCurrentWeatherData(
            lat, lon, units, ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER)
        )
        return response?.toCurrentWeatherData()
    }

    override suspend fun getForecast(
        lat: Double, lon: Double, units: String
    ): ForecastWeatherData? {
        val response = service?.getForecastWeather(
            lat, lon, units, ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER)
        )
        return response?.toForecastWeatherData()
    }
}

fun getOWWeatherRemoteDataSourceImpl(client: OkHttpClient): WeatherRemoteDataSource {
    return OWWeatherRemoteDataSourceImpl(client)
}