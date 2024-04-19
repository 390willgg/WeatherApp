package com.example.weatherappsample1yt.model

data class ForecastDailyData(
    val date: String,
    val maxTemp: Double?,
    val minTemp: Double?,
    val weatherIcon: String?
)
