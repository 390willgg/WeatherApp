package com.example.weatherappsample1yt.server

import com.example.weatherappsample1yt.model.weather_api.CityResponseAPI_WeatherAPI
import com.example.weatherappsample1yt.model.weather_api.CurrentResponseAPI_WeatherAPI
import com.example.weatherappsample1yt.model.weather_api.ForecastResponseAPI_WeatherAPI
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService_WeatherAPI {
    @GET("current.json")
    suspend fun getCurrentWeatherData(
        @Query("q") location: String
    ): CurrentResponseAPI_WeatherAPI

    @GET("forecast.json")
    suspend fun getForecastWeatherData(
        @Query("q") location: String,
        @Query("days") days: Int
    ): ForecastResponseAPI_WeatherAPI

    @GET("search.json")
    suspend fun search(
        @Query("q") query: String
    ): CityResponseAPI_WeatherAPI
}