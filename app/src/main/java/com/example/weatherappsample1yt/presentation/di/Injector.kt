package com.example.weatherappsample1yt.presentation.di

import com.example.weatherappsample1yt.presentation.di.cityList.CityListSubComponent
import com.example.weatherappsample1yt.presentation.di.weather.WeatherSubComponent

interface Injector {
    fun createWeatherSubComponent(): WeatherSubComponent
    fun createCitySubComponent(): CityListSubComponent
}