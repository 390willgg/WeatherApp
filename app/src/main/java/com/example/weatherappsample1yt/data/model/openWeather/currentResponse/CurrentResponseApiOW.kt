package com.example.weatherappsample1yt.data.model.openWeather.currentResponse

import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.openWeather.forecastResponse.Rain
import com.google.gson.annotations.SerializedName

data class CurrentResponseApiOW(
    @SerializedName("base") val base: String?,
    @SerializedName("clouds") val clouds: Clouds?,
    @SerializedName("cod") val cod: Int?,
    @SerializedName("coord") val coord: Coord?,
    @SerializedName("dt") val dt: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("main") val main: Main?,
    @SerializedName("name") val name: String?,
    @SerializedName("rain") val rain: Rain?,
    @SerializedName("sys") val sys: Sys?,
    @SerializedName("timezone") val timezone: Int?,
    @SerializedName("visibility") val visibility: Int?,
    @SerializedName("weather") val weather: List<Weather?>?,
    @SerializedName("wind") val wind: Wind?
)

    fun CurrentResponseApiOW.toCurrentWeatherData(): CurrentWeatherData {
        return CurrentWeatherData(
            city = this.name ?: "",
            country = this.sys?.country ?: "",
            latitude = this.coord?.lat ?: 0.0,
            longitude = this.coord?.lon ?: 0.0,
            temperature = this.main?.temp ?: 0.0,
            maxTemperature = this.main?.tempMax ?: 0.0,
            minTemperature = this.main?.tempMin ?: 0.0,
            weatherStatus = this.weather?.get(0)?.main ?: "",
            weatherDescription = this.weather?.get(0)?.description ?: "",
            weatherIcon = this.weather?.get(0)?.icon ?: "",
            windSpeed = this.wind?.speed ?: 0.0,
            humidity = this.main?.humidity ?: 0,
            icon = this.weather?.get(0)?.icon ?: "",
            precipitation = this.rain?.h ?: 0.0
        )
    }
