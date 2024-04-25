package com.example.weatherappsample1yt.data.model.aiMeteoSource

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cloud_cover")
    val cloudCover: Int?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("dew_point")
    val dewPoint: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("icon")
    val icon: Int?,
    @SerializedName("ozone")
    val ozone: Double?,
    @SerializedName("precipitation")
    val precipitation: Precipitation?,
    @SerializedName("pressure")
    val pressure: Double?,
    @SerializedName("probability")
    val probability: Probability?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("temperature")
    val temperature: Double?,
    @SerializedName("uv_index")
    val uvIndex: Double?,
    @SerializedName("visibility")
    val visibility: Double?,
    @SerializedName("weather")
    val weather: String?,
    @SerializedName("wind")
    val wind: Wind?,
    @SerializedName("wind_chill")
    val windChill: Double?
)