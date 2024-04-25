package com.example.weatherappsample1yt.data.model.aiMeteoSource.currentResponse

import com.example.weatherappsample1yt.data.model.aiMeteoSource.Precipitation
import com.example.weatherappsample1yt.data.model.aiMeteoSource.Wind
import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("cloud_cover")
    val cloudCover: Int?,
    @SerializedName("dew_point")
    val dewPoint: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("icon_num")
    val iconNum: Int?,
    @SerializedName("ozone")
    val ozone: Double?,
    @SerializedName("precipitation")
    val precipitation: Precipitation?,
    @SerializedName("pressure")
    val pressure: Double?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("temperature")
    val temperature: Double?,
    @SerializedName("uv_index")
    val uvIndex: Double?,
    @SerializedName("visibility")
    val visibility: Double?,
    @SerializedName("wind")
    val wind: Wind?,
    @SerializedName("wind_chill")
    val windChill: Double?
)
