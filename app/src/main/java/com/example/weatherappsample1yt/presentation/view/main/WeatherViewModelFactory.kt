package com.example.weatherappsample1yt.presentation.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappsample1yt.domain.useCase.weather.WeatherUseCase

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
    private val weatherUseCase: WeatherUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                return WeatherViewModel(weatherUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}