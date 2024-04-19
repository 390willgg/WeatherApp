package com.example.weatherappsample1yt.server

import com.example.weatherappsample1yt.model.ai_weather_meteosource.CityResponseAPI_Ai_MeteoSource
import com.example.weatherappsample1yt.model.ai_weather_meteosource.CurrentResponseAPI_AI_MeteoSource
import com.example.weatherappsample1yt.model.ai_weather_meteosource.ForecastDailyResponseAPI_AI_MeteoSource
import com.example.weatherappsample1yt.model.ai_weather_meteosource.ForecastHourlyResponseAPI_AI_MeteoSource
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService_AIMeteoSource {
    @GET("current")
    fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("timezone") timezone: String,
        @Query("language") language: String,
        @Query("units") units: String
    ): CurrentResponseAPI_AI_MeteoSource

    @GET("hourly")
    fun getHourlyWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("timezone") timezone: String,
        @Query("language") language: String,
        @Query("units") units: String
    ): ForecastHourlyResponseAPI_AI_MeteoSource

    @GET("daily")
    fun getDailyWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("language") language: String,
        @Query("units") units: String
    ): ForecastDailyResponseAPI_AI_MeteoSource

    @GET("find_places_prefix")
    fun findPlacesPrefix(
        @Query("text") text: String,
        @Query("language") language: String
    ): CityResponseAPI_Ai_MeteoSource
}