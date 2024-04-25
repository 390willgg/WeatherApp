package com.example.weatherappsample1yt.presentation.view.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.domain.useCase.city.CitiesListUseCase
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val citiesListUseCase: CitiesListUseCase
): ViewModel() {
    private val _citiesList = MutableLiveData<CityWeatherData?>()
    val citiesList: MutableLiveData<CityWeatherData?> = _citiesList

    fun getCitiesList(cityName: String, limit: Int) {
        viewModelScope.launch {
            val cityData = citiesListUseCase.getCitiesList(cityName, limit)
            _citiesList.value = cityData
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}