package com.example.weatherappsample1yt.data.model.openWeather.forecastResponse

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg") val deg: Int?,
    @SerializedName("gust") val gust: Double?,
    @SerializedName("speed") val speed: Double?
)