package com.example.weatherappsample1yt.domain.useCase.city

import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.domain.repository.CityRepository

private class CitiesListUseCaseImpl (private val repository: CityRepository) :
    CitiesListUseCase {
    override suspend fun getCitiesList(cityName: String, limit: Int): CityWeatherData? {
        return repository.getCitiesList(cityName, limit)
    }
}

fun getCitiesListUseCaseImpl(repository: CityRepository): CitiesListUseCase =
    CitiesListUseCaseImpl(repository)

