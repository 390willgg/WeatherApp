package com.example.weatherappsample1yt.data.model.format

data class CurrentWeatherData(
    val icon: String?,
    val city: String?,
    val country: String?,
    val latitude: Double?,
    val longitude: Double?,
    val temperature: Double?,
    val maxTemperature: Double?,
    val minTemperature: Double?,
    val weatherStatus: String?,
    val weatherDescription: String?,
    val weatherIcon: String?,
    val windSpeed: Double?,
    val humidity: Int?,
    val precipitation: Double?,
)
