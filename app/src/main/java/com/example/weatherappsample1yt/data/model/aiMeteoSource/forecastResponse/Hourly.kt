package com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse

import com.example.weatherappsample1yt.data.model.aiMeteoSource.Data
import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("data") val `data`: List<Data?>?
)