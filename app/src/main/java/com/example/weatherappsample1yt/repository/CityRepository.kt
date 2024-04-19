package com.example.weatherappsample1yt.repository

import com.example.weatherappsample1yt.server.ApiKeyProvider
import com.example.weatherappsample1yt.server.ApiProvider
import com.example.weatherappsample1yt.server.ApiService_OpenWeather

class CityRepository(private val api: ApiService_OpenWeather) {
    fun getCitiesList(cityName: String, limit: Int) =
        api.getCitiesList(cityName, limit, ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER))
}