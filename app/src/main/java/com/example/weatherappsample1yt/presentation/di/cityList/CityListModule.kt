package com.example.weatherappsample1yt.presentation.di.cityList

import com.example.weatherappsample1yt.domain.useCase.city.CitiesListUseCase
import com.example.weatherappsample1yt.presentation.view.city.CityViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CityModule {
    @CityListScope
    @Provides
    fun provideCityListViewModelFactory(
        useCase: CitiesListUseCase
    ): CityViewModelFactory {
        return CityViewModelFactory(useCase)
    }
}