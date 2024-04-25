package com.example.weatherappsample1yt.data.model.openWeather.currentResponse

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country") val country: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("sunrise") val sunrise: Int?,
    @SerializedName("sunset") val sunset: Int?,
    @SerializedName("type") val type: Int?,
    @SerializedName("pod") val pod: String?
)
