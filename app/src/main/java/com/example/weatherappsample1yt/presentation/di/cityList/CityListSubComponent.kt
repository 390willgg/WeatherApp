package com.example.weatherappsample1yt.presentation.di.cityList

import com.example.weatherappsample1yt.presentation.view.city.CityActivity
import dagger.Subcomponent

@CityListScope
@Subcomponent(modules = [CityModule::class])
interface CityListSubComponent {
    fun inject(cityListActivity: CityActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():CityListSubComponent
    }
}

