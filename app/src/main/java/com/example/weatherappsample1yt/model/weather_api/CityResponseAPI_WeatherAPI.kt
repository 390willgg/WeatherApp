package com.example.weatherappsample1yt.model.weather_api


import com.example.weatherappsample1yt.model.format.CityWeatherData
import com.google.gson.annotations.SerializedName

class CityResponseAPI_WeatherAPI : ArrayList<CityResponseAPI_WeatherAPI.CityResponseAPI_WeatherAPIItem>(){
    data class CityResponseAPI_WeatherAPIItem(
        @SerializedName("country")
        val country: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("lon")
        val lon: Double?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("url")
        val url: String?
    )

    fun toCityWeatherData(): CityWeatherData {
        return CityWeatherData(
            country = this[0].country,
            placeId = this[0].id.toString(),
            latitude = this[0].lat,
            longitude = this[0].lon,
            cityName = this[0].name,
            timezone = this[0].region,
        )
    }
}