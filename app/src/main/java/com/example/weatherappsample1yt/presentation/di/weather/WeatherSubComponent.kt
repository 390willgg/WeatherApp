package com.example.weatherappsample1yt.presentation.di.weather

import com.example.weatherappsample1yt.presentation.view.main.MainActivity
import dagger.Subcomponent

@WeatherScope
@Subcomponent(modules = [WeatherModule::class])
interface WeatherSubComponent {
    fun inject(weatherActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():WeatherSubComponent
    }
}

