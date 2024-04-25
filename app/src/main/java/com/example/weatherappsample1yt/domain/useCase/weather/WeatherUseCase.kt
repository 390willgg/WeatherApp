package com.example.weatherappsample1yt.domain.useCase.weather

import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData

interface WeatherUseCase {
    suspend fun getCurrentWeather(lat: Double, lon: Double, units: String): CurrentWeatherData?
    suspend fun getForecastWeather(lat: Double, lon: Double, units: String): ForecastWeatherData?
}