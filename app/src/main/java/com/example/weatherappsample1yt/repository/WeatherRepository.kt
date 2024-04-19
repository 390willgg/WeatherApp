package com.example.weatherappsample1yt.repository

import com.example.weatherappsample1yt.server.ApiService_OpenWeather
import com.example.weatherappsample1yt.server.ApiKeyProvider
import com.example.weatherappsample1yt.server.ApiProvider
import com.example.weatherappsample1yt.server.ApiService_AIMeteoSource
import com.example.weatherappsample1yt.server.ApiService_WeatherAPI

class WeatherRepository {
    private lateinit var openWeatherService: ApiService_OpenWeather
    private lateinit var weatherApiService: ApiService_WeatherAPI
    private lateinit var aiMeteoSource: ApiService_AIMeteoSource

    suspend fun getCurrentWeatherData(
        api_provider: ApiProvider,
        lat: Double,
        lon: Double,
        units: String
    ) {
        when (api_provider) {
            ApiProvider.OPEN_WEATHER -> {
                openWeatherService.getCurrentWeatherData(
                    lat,
                    lon,
                    units,
                    ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER)
                )
            }

            ApiProvider.WEATHER_API -> {
                weatherApiService.getCurrentWeatherData("$lat, $lon")
            }

            ApiProvider.METEO_STAT -> {
                aiMeteoSource.getCurrentWeatherData(lat, lon, "auto", "en", "metric")
            }
        }
    }

    suspend fun getForecastWeatherData(
        api_provider: ApiProvider,
        lat: Double,
        lon: Double,
        units: String
    ) {
        when (api_provider) {
            ApiProvider.OPEN_WEATHER -> {
                openWeatherService.getForecastWeather(
                    lat,
                    lon,
                    units,
                    ApiKeyProvider.getApiKey(ApiProvider.OPEN_WEATHER)
                )
            }

            ApiProvider.WEATHER_API -> {
                weatherApiService.getForecastWeatherData("$lat, $lon", 7)
            }

            ApiProvider.METEO_STAT -> {
                aiMeteoSource.getHourlyWeatherData(lat, lon, "auto", "en", "metric")
            }
        }
    }
}


