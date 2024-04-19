package com.example.weatherappsample1yt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappsample1yt.model.open_weather.CityResponseApi_OpenWeather
import com.example.weatherappsample1yt.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.cancelChildren

class CityViewModel(private val repository: CityRepository): ViewModel() {
    fun loadCity(q: String, limit: Int, onSuccess: (CityResponseApi_OpenWeather) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val call = repository.getCitiesList(q, limit)
            call.enqueue(object : Callback<CityResponseApi_OpenWeather> {
                override fun onResponse(call: Call<CityResponseApi_OpenWeather>, response: Response<CityResponseApi_OpenWeather>) {
                    if (response.isSuccessful) {
                        response.body()?.let(onSuccess)
                    } else {
                        onError(RuntimeException("Error: ${response.errorBody()}"))
                    }
                }

                override fun onFailure(call: Call<CityResponseApi_OpenWeather>, t: Throwable) {
                    onError(t)
                }
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}