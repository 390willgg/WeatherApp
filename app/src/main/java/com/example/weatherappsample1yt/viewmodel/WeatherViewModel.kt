package com.example.weatherappsample1yt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsample1yt.model.open_weather.CurrentResponseApi_OpenWeather
import com.example.weatherappsample1yt.model.open_weather.ForecastResponseApi_OpenWeather
import com.example.weatherappsample1yt.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentResponseApi_OpenWeather>()
    val currentWeather: LiveData<CurrentResponseApi_OpenWeather> = _currentWeather

    private val _forecastWeather = MutableLiveData<ForecastResponseApi_OpenWeather>()
    val forecastWeather: LiveData<ForecastResponseApi_OpenWeather> = _forecastWeather

    fun loadCurrentWeather(lat: Double, lon: Double, unit: String) {
        viewModelScope.launch {
            try {
                val result = repository.getCurrentWeatherData(lat, lon, unit)
                _currentWeather.postValue(result)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading current weather", e)
            }
        }
    }

    fun loadForecastWeather(lat: Double, lon: Double, unit: String) {
        viewModelScope.launch {
            try {
                val result = repository.getForecastWeatherData(lat, lon, unit)
                _forecastWeather.postValue(result)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading forecast weather", e)
            }
        }
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}
