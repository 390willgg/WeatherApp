package com.example.weatherappsample1yt.data.model.weatherAPI

import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.data.model.format.DataItemCity
import com.google.gson.annotations.SerializedName

class CityResponseApiWA : ArrayList<CityResponseApiWA.CityResponseAPIWeatherAPIItem>(){
    data class CityResponseAPIWeatherAPIItem(
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
}

fun CityResponseApiWA.toCityWeatherData(): CityWeatherData {
    val dataItems = this.map { cityResponseItem ->
        DataItemCity(
            cityName = cityResponseItem.name,
            country = cityResponseItem.country,
            latitude = cityResponseItem.lat,
            longitude = cityResponseItem.lon,
            placeId = cityResponseItem.id.toString(),
            timezone = cityResponseItem.region,
            region = null,
            idCity = null,
            typeArea = null,
            localNames = null
        )
    }
    return CityWeatherData(data = ArrayList(dataItems))
}