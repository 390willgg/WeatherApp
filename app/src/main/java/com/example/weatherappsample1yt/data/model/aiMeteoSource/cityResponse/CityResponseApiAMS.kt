package com.example.weatherappsample1yt.data.model.aiMeteoSource.cityResponse


import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.data.model.format.DataItemCity
import com.google.gson.annotations.SerializedName

class CityResponseApiAMS : ArrayList<CityResponseApiAMS.CityResponseAPIAIMeteoSourceItem>(){
    data class CityResponseAPIAIMeteoSourceItem(
        @SerializedName("adm_area1")
        val admArea1: String?,
        @SerializedName("adm_area2")
        val admArea2: Any?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: String?,
        @SerializedName("lon")
        val lon: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("place_id")
        val placeId: String?,
        @SerializedName("timezone")
        val timezone: String?,
        @SerializedName("type")
        val type: String?
    )
}

fun CityResponseApiAMS.toCityWeatherData(): CityWeatherData {
    val dataItems = this.map { cityResponseItem ->
        DataItemCity(
            cityName = cityResponseItem.name,
            country = cityResponseItem.country,
            latitude = cityResponseItem.lat?.toDouble(),
            longitude = cityResponseItem.lon?.toDouble(),
            placeId = cityResponseItem.placeId,
            timezone = cityResponseItem.timezone,
            region = cityResponseItem.admArea1,
            idCity = null,
            typeArea = cityResponseItem.type,
            localNames = null
        )
    }
    return CityWeatherData(data = ArrayList(dataItems))
}