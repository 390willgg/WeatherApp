package com.example.weatherappsample1yt.domain.repository

import com.example.weatherappsample1yt.data.model.format.CityWeatherData

interface CityRepository {
    suspend fun getCitiesList(cityName: String, limit: Int): CityWeatherData?
}
