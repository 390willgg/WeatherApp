package com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse

import com.example.weatherappsample1yt.data.model.format.ForecastDetail
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.model.format.HourlyDetail
import com.google.gson.annotations.SerializedName

data class ForecastHourlyResponseApiAMS(
    @SerializedName("elevation") val elevation: Int?,
    @SerializedName("hourly") val hourly: Hourly?,
    @SerializedName("lat") val lat: String?,
    @SerializedName("lon") val lon: String?,
    @SerializedName("timezone") val timezone: String?,
    @SerializedName("units") val units: String?
)

fun ForecastHourlyResponseApiAMS.toForecastWeatherData(): ForecastWeatherData {
    val hourlyDetails = this.hourly?.data?.map { data ->
        HourlyDetail(
            time = data?.date,
            temp = data?.temperature,
            feelsLike = data?.feelsLike,
            condition = data?.weather,
            icon = data?.icon.toString(),
            description = data?.summary,
            uvIndex = data?.uvIndex,
            maxTemp = null, // You might need to fill this from another source
            minTemp = null, // You might need to fill this from another source
            windSpeed = data?.wind?.speed,
            windDirection = data?.wind?.angle.toString(),
            pressure = data?.pressure,
            humidity = data?.humidity,
            precipitation = data?.precipitation?.total
        )
    }

    val latitude = this.lat?.replace(Regex("[^\\d.]"), "")?.toDoubleOrNull() ?: 0.0
    val longitude = this.lon?.replace(Regex("[^\\d.]"), "")?.toDoubleOrNull() ?: 0.0

    return ForecastWeatherData(
        country = null, // You might need to fill this from another source
        city = null, // You might need to fill this from another source
        lat = latitude, lon = longitude, timezone = this.timezone, forecasts = ForecastDetail(
            dailyDetails = null, // You might need to fill this from another source
            hourlyDetails = hourlyDetails
        )
    )
}