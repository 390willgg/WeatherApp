package com.example.weatherappsample1yt.data.model.format

data class ForecastWeatherData(
    val country: String?,
    val city: String?,
    val lat: Double?,
    val lon: Double?,
    val timezone: String?, // Added timezone field
    val forecasts: ForecastDetail?
)

data class ForecastDetail(
    val dailyDetails: List<DailyDetail>?,
    val hourlyDetails: List<HourlyDetail>?
)

data class DailyDetail(
    val date: String?,
    val temp: Double?,
    val maxTemp: Double?,
    val minTemp: Double?,
    val condition: String?,
    val icon: String?,
    val description: String?,
    val precipitation: Double?,
    val pressure: Double?,
    val humidity: Int?,
    val windSpeed: Double?,
    val windDirection: String?,
    val uvIndex: Double? // Added uvIndex field
)

data class HourlyDetail(
    val time: String?,
    val temp: Double?,
    val maxTemp: Double?,
    val minTemp: Double?,
    val feelsLike: Double?,
    val condition: String?,
    val icon: String?,
    val pressure: Double?,
    val humidity: Int?,
    val windSpeed: Double?,
    val windDirection: String?,
    val description: String?,
    val precipitation: Double?,
    val uvIndex: Double? // Added uvIndex field
)