package com.example.weatherappsample1yt.presentation.di.core

import android.content.Context
import com.example.weatherappsample1yt.presentation.di.cityList.CityListSubComponent
import com.example.weatherappsample1yt.presentation.di.weather.WeatherSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [CityListSubComponent::class, WeatherSubComponent::class])
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}