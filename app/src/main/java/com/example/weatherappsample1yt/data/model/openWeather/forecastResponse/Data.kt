package com.example.weatherappsample1yt.data.model.openWeather.forecastResponse

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("clouds") val clouds: Clouds?,
    @SerializedName("dt") val dt: Int?,
    @SerializedName("dt_txt") val dtTxt: String?,
    @SerializedName("main") val main: Main?,
    @SerializedName("pop") val pop: Double?,
    @SerializedName("rain") val rain: Rain?,
    @SerializedName("sys") val sys: Sys?,
    @SerializedName("visibility") val visibility: Int?,
    @SerializedName("weather") val weather: List<Weather?>?,
    @SerializedName("wind") val wind: Wind?
)
