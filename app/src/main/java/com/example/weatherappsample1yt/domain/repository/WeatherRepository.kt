package com.example.weatherappsample1yt.domain.repository

import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData

interface WeatherRepository {
    suspend fun getCurrentWeatherData(lat: Double, lon: Double, units: String): CurrentWeatherData?
    suspend fun getForecastWeatherData(
        lat: Double,
        lon: Double,
        units: String
    ): ForecastWeatherData?
}




