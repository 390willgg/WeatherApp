package com.example.weatherappsample1yt.server

import com.example.weatherappsample1yt.model.open_weather.CityResponseApi_OpenWeather
import com.example.weatherappsample1yt.model.open_weather.CurrentResponseApi_OpenWeather
import com.example.weatherappsample1yt.model.open_weather.ForecastResponseApi_OpenWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService_OpenWeather {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): CurrentResponseApi_OpenWeather

    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): ForecastResponseApi_OpenWeather

    @GET("geo/1.0/direct")
    fun getCitiesList(
        @Query("q") cityName: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): Call<CityResponseApi_OpenWeather>
}
