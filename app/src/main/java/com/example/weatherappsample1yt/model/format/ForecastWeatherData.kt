package com.example.weatherappsample1yt.model.format

data class ForecastWeatherData(
    val country: String?,
    val city: String?,
    val lat: Double?,
    val lon: Double?,
    val forecasts: List<ForecastDetail>
)

data class ForecastDetail(
    val date: String?,
    val temperature: Double?,
    val temperatureMin: Double?,
    val temperatureMax: Double?,
    val pressure: Double?,
    val humidity: Int?,
    val windSpeed: Double?,
    val windDirection: String?,
    val icon: String?,
    val description: String?,
    val dailyDetails: List<DailyDetail>?,
    val hourlyDetails: List<HourlyDetail>?
)

data class DailyDetail(
    val date: String?,
    val maxTemp: Double?,
    val minTemp: Double?,
    val condition: String?,
    val precipitation: Double?
)

data class HourlyDetail(
    val time: String?,
    val temp: Double?,
    val feelsLike: Double?,
    val condition: String?,
    val precipitation: Double?
)
