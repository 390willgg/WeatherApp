package com.example.weatherappsample1yt.data.model.format

data class CityWeatherData(
    val data: ArrayList<DataItemCity>
)

data class DataItemCity(
    val cityName: String?,
    val country: String?,
    val latitude: Double?,
    val longitude: Double?,
    val placeId: String?,
    val timezone: String?,
    val region: String?,
    val idCity: Int?,
    val typeArea: String?,
    val localNames: Map<String, String?>?
)
