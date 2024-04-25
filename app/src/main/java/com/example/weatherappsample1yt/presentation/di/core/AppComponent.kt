package com.example.weatherappsample1yt.presentation.di.core

import com.example.weatherappsample1yt.presentation.di.cityList.CityListSubComponent
import com.example.weatherappsample1yt.presentation.di.weather.WeatherSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteDataModule::class,
        UseCaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun weatherSubComponent(): WeatherSubComponent.Factory
    fun citySubComponent(): CityListSubComponent.Factory
}