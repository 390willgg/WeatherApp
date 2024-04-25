package com.example.weatherappsample1yt.presentation.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.domain.useCase.weather.WeatherUseCase
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

//class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
//    private val _currentWeather = MutableLiveData<CurrentResponseApiOW>()
//    val currentWeather: LiveData<CurrentResponseApiOW> = _currentWeather
//
//    private val _forecastWeather = MutableLiveData<ForecastResponseApiOW>()
//    val forecastWeather: LiveData<ForecastResponseApiOW> = _forecastWeather
//
//    fun loadCurrentWeather(lat: Double, lon: Double, unit: String) {
//        viewModelScope.launch {
//            try {
//                val result = repository.getCurrentWeatherData(lat, lon, unit)
//                _currentWeather.postValue(result)
//            } catch (e: Exception) {
//                Log.e(TAG, "Error loading current weather", e)
//            }
//        }
//    }
//
//    fun loadForecastWeather(lat: Double, lon: Double, unit: String) {
//        viewModelScope.launch {
//            try {
//                val result = repository.getForecastWeatherData(lat, lon, unit)
//                _forecastWeather.postValue(result)
//            } catch (e: Exception) {
//                Log.e(TAG, "Error loading forecast weather", e)
//            }
//        }
//    }
//
//    companion object {
//        private const val TAG = "WeatherViewModel"
//    }
//}

class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeatherData?>()
    val currentWeather: MutableLiveData<CurrentWeatherData?> = _currentWeather

    private val _forecastWeather = MutableLiveData<ForecastWeatherData?>()
    val forecastWeather: MutableLiveData<ForecastWeatherData?> = _forecastWeather

    fun getCurrentWeather(lat: Double, lon: Double, units: String) {
        viewModelScope.launch {
            val currentWeather = weatherUseCase.getCurrentWeather(lat, lon, units)
            _currentWeather.postValue(currentWeather)
        }
    }

    fun getForecastWeather(lat: Double, lon: Double, units: String) {
        viewModelScope.launch {
            val forecastWeather = weatherUseCase.getForecastWeather(lat, lon, units)
            _forecastWeather.postValue(forecastWeather)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}