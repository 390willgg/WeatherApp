package com.example.weatherappsample1yt.domain.useCase.city

import com.example.weatherappsample1yt.data.model.format.CityWeatherData

interface CitiesListUseCase {
    suspend fun getCitiesList(cityName: String, limit: Int): CityWeatherData?
}