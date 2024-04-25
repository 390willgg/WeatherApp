package com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse

import com.google.gson.annotations.SerializedName

data class ForecastDailyResponseApiAMS(
    @SerializedName("daily") val daily: Daily?,
    @SerializedName("elevation") val elevation: Int?,
    @SerializedName("lat") val lat: String?,
    @SerializedName("lon") val lon: String?,
    @SerializedName("units") val units: String?
)