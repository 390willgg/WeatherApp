package com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse

import com.example.weatherappsample1yt.data.model.aiMeteoSource.Data
import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("data") val `data`: List<com.example.weatherappsample1yt.data.model.aiMeteoSource.Data?>?
)