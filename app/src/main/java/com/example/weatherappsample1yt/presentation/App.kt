package com.example.weatherappsample1yt.presentation

import android.app.Application
import com.example.weatherappsample1yt.presentation.di.Injector
import com.example.weatherappsample1yt.presentation.di.cityList.CityListSubComponent
import com.example.weatherappsample1yt.presentation.di.core.AppComponent
import com.example.weatherappsample1yt.presentation.di.core.DaggerAppComponent
import com.example.weatherappsample1yt.presentation.di.weather.WeatherSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    override fun createWeatherSubComponent(): WeatherSubComponent {
        return appComponent.weatherSubComponent().create()
    }

    override fun createCitySubComponent(): CityListSubComponent {
        return appComponent.citySubComponent().create()
    }
}