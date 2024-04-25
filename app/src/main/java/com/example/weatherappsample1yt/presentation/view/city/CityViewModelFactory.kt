package com.example.weatherappsample1yt.presentation.view.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappsample1yt.domain.useCase.city.CitiesListUseCase

@Suppress("UNCHECKED_CAST")
class CityViewModelFactory(
    private val citiesListUseCase: CitiesListUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(citiesListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}