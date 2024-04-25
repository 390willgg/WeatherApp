package com.example.weatherappsample1yt.presentation.di.weather

import com.example.weatherappsample1yt.domain.useCase.weather.WeatherUseCase
import com.example.weatherappsample1yt.presentation.view.main.WeatherViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {
    @WeatherScope
    @Provides
    fun provideWeatherViewModelFactory(weatherUseCase: WeatherUseCase): WeatherViewModelFactory {
        return WeatherViewModelFactory(weatherUseCase)
    }
}