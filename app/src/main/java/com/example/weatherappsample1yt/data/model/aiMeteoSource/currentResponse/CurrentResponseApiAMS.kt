package com.example.weatherappsample1yt.data.model.aiMeteoSource.currentResponse

import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.google.gson.annotations.SerializedName

data class CurrentResponseApiAMS(
    @SerializedName("current") val current: Current?,
    @SerializedName("elevation") val elevation: Int?,
    @SerializedName("lat") val lat: String?,
    @SerializedName("lon") val lon: String?,
    @SerializedName("timezone") val timezone: String?,
    @SerializedName("units") val units: String?
)

fun CurrentResponseApiAMS.toCurrentWeatherData(): CurrentWeatherData {
    val latitude = lat?.replace(Regex("[^\\d.]"), "")?.toDoubleOrNull() ?: 0.0
    val longitude = lon?.replace(Regex("[^\\d.]"), "")?.toDoubleOrNull() ?: 0.0

    return CurrentWeatherData(
        city = null,
        country = null,
        latitude = latitude,
        longitude = longitude,
        temperature = current?.temperature ?: 0.0,
        maxTemperature = current?.temperature ?: 0.0,
        minTemperature = current?.temperature ?: 0.0,
        weatherStatus = current?.summary ?: "",
        weatherDescription = current?.summary ?: "",
        weatherIcon = current?.icon ?: "",
        windSpeed = current?.wind?.speed ?: 0.0,
        humidity = current?.humidity ?: 0,
        icon = current?.icon ?: "",
        precipitation = current?.precipitation?.total ?: 0.0
    )
}