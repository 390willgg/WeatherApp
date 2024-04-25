package com.example.weatherappsample1yt.data.api.server

import com.example.weatherappsample1yt.data.model.aiMeteoSource.cityResponse.CityResponseApiAMS
import com.example.weatherappsample1yt.data.model.aiMeteoSource.currentResponse.CurrentResponseApiAMS
import com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse.ForecastDailyResponseApiAMS
import com.example.weatherappsample1yt.data.model.aiMeteoSource.forecastResponse.ForecastHourlyResponseApiAMS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceAMS {
    @GET("current")
    suspend fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("timezone") timezone: String,
        @Query("language") language: String,
        @Query("units") units: String
    ): Response<CurrentResponseApiAMS>

    @GET("hourly")
    suspend fun getHourlyWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("timezone") timezone: String,
        @Query("language") language: String,
        @Query("units") units: String
    ): Response<ForecastHourlyResponseApiAMS>

    @GET("daily")
    fun getDailyWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("language") language: String,
        @Query("units") units: String
    ): ForecastDailyResponseApiAMS

    @GET("find_places_prefix")
    fun getCitiesWeatherData(
        @Query("text") text: String,
        @Query("language") language: String
    ): CityResponseApiAMS
}