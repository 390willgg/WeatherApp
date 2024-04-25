package com.example.weatherappsample1yt.presentation.di.core

import com.example.weatherappsample1yt.domain.repository.CityRepository
import com.example.weatherappsample1yt.domain.repository.WeatherRepository
import com.example.weatherappsample1yt.domain.useCase.city.CitiesListUseCase
import com.example.weatherappsample1yt.domain.useCase.city.getCitiesListUseCaseImpl
import com.example.weatherappsample1yt.domain.useCase.weather.WeatherUseCase
import com.example.weatherappsample1yt.domain.useCase.weather.getWeatherUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    @Named("WAWeather")
    fun provideWAWeatherUseCase(@Named("WAWeather") repository: WeatherRepository): WeatherUseCase = getWeatherUseCaseImpl(repository)

    @Singleton
    @Provides
    @Named("OWWeather")
    fun provideOWWeatherUseCase(@Named("OWWeather") repository: WeatherRepository): WeatherUseCase = getWeatherUseCaseImpl(repository)

    @Singleton
    @Provides
    @Named("AMSWeather")
    fun provideAMSWeatherUseCase(@Named("AMSWeather") repository: WeatherRepository): WeatherUseCase = getWeatherUseCaseImpl(repository)

    @Singleton
    @Provides
    @Named("AMSCity")
    fun provideAMSCityListUseCase(@Named("AMSCity") repository: CityRepository): CitiesListUseCase = getCitiesListUseCaseImpl(repository)

    @Singleton
    @Provides
    @Named("OWCity")
    fun provideOWCityListUseCase(@Named("OWCity") repository: CityRepository): CitiesListUseCase = getCitiesListUseCaseImpl(repository)

    @Singleton
    @Provides
    @Named("WACity")
    fun provideAWCityListUseCase(@Named("WACity") repository: CityRepository): CitiesListUseCase = getCitiesListUseCaseImpl(repository)
}