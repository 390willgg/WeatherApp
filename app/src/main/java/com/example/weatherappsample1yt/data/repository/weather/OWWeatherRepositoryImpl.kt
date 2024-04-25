package com.example.weatherappsample1yt.data.repository.weather

import android.util.Log
import com.example.weatherappsample1yt.data.api.client.ApiKeyProvider
import com.example.weatherappsample1yt.data.api.client.ApiProvider
import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.repository.weather.dataSource.WeatherRemoteDataSource
import com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl.getOWWeatherRemoteDataSourceImpl
import com.example.weatherappsample1yt.domain.repository.WeatherRepository
import okhttp3.OkHttpClient

private class OWWeatherRepositoryImpl : WeatherRepository {
    private val apiKey = ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER)
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("Authorization", "Bearer $apiKey")

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val weatherRemoteDataSource: WeatherRemoteDataSource =
        getOWWeatherRemoteDataSourceImpl(client)

    override suspend fun getCurrentWeatherData(
        lat: Double,
        lon: Double,
        units: String
    ): CurrentWeatherData? {
        Log.d("Repository", "Using OWWeatherRepository for getCurrentWeatherData")
        return weatherRemoteDataSource.getCurrentWeather(lat, lon, units)
    }

    override suspend fun getForecastWeatherData(
        lat: Double,
        lon: Double,
        units: String
    ): ForecastWeatherData? {
        Log.d("Repository", "Using OWWeatherRepository for getForecastWeatherData")
        return weatherRemoteDataSource.getForecast(lat, lon, units)
    }
}

fun getOWWeatherRepositoryImpl(): WeatherRepository {
    Log.d("Repository", "Creating OWWeatherRepository")
    return OWWeatherRepositoryImpl()
}