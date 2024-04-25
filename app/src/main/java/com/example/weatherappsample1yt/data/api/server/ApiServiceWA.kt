package com.example.weatherappsample1yt.data.api.server

import com.example.weatherappsample1yt.data.model.weatherAPI.CityResponseApiWA
import com.example.weatherappsample1yt.data.model.weatherAPI.CurrentResponseApiWA
import com.example.weatherappsample1yt.data.model.weatherAPI.ForecastResponseApiWA
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceWA {
    @GET("current.json")
    suspend fun getCurrentWeatherData(
        @Query("q") location: String
    ): CurrentResponseApiWA

    @GET("forecast.json")
    suspend fun getForecastWeatherData(
        @Query("q") location: String,
        @Query("days") days: Int
    ): ForecastResponseApiWA

    @GET("search.json")
    suspend fun getCityWeatherData(
        @Query("q") query: String
    ): CityResponseApiWA
}