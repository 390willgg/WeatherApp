package com.example.weatherappsample1yt.data.model.openWeather.forecastResponse

import com.example.weatherappsample1yt.data.model.format.ForecastDetail
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.model.format.HourlyDetail
import com.google.gson.annotations.SerializedName

data class ForecastResponseApiOW(
    @SerializedName("city") val city: City?,
    @SerializedName("cnt") val cnt: Int?,
    @SerializedName("cod") val cod: String?,
    @SerializedName("list") val list: List<Data>?,
    @SerializedName("message") val message: Int?
)


fun ForecastResponseApiOW.toForecastWeatherData(): ForecastWeatherData {
    val hourlyDetails = this.list?.map { data ->
        HourlyDetail(
            time = data.dtTxt,
            temp = data.main?.temp,
            maxTemp = data.main?.tempMax,
            minTemp = data.main?.tempMin,
            feelsLike = data.main?.feelsLike,
            condition = data.weather?.firstOrNull()?.description,
            icon = data.weather?.firstOrNull()?.icon,
            pressure = data.main?.pressure?.toDouble(),
            humidity = data.main?.humidity,
            windSpeed = data.wind?.speed,
            windDirection = data.wind?.deg?.toString(),
            description = data.weather?.firstOrNull()?.description,
            precipitation = data.rain?.h,
            uvIndex = null // UV index data is not available in the current API response
        )
    } ?: listOf()

    return ForecastWeatherData(
        country = this.city?.country,
        city = this.city?.name,
        lat = this.city?.coord?.lat,
        lon = this.city?.coord?.lon,
        timezone = this.city?.timezone.toString(),
        forecasts = ForecastDetail(hourlyDetails = hourlyDetails, dailyDetails = null)
    )
}