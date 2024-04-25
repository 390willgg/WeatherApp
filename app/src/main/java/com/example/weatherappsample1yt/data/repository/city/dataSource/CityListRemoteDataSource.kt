package com.example.weatherappsample1yt.data.repository.city.dataSource

import com.example.weatherappsample1yt.data.model.format.CityWeatherData

interface CityListRemoteDataSource {
    suspend fun getCities(cityName: String, limit: Int): CityWeatherData?
}