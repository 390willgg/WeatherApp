package com.example.weatherappsample1yt.model.open_weather


import com.example.weatherappsample1yt.model.format.ForecastDetail
import com.example.weatherappsample1yt.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.model.format.HourlyDetail
import com.google.gson.annotations.SerializedName

data class ForecastResponseApi_OpenWeather(
    @SerializedName("city") val city: City?,
    @SerializedName("cnt") val cnt: Int?,
    @SerializedName("cod") val cod: String?,
    @SerializedName("list") val list: List<Data>?,
    @SerializedName("message") val message: Int?
) {
    data class City(
        @SerializedName("coord") val coord: Coord?,
        @SerializedName("country") val country: String?,
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("population") val population: Int?,
        @SerializedName("sunrise") val sunrise: Int?,
        @SerializedName("sunset") val sunset: Int?,
        @SerializedName("timezone") val timezone: Int?
    ) {
        data class Coord(
            @SerializedName("lat") val lat: Double?, @SerializedName("lon") val lon: Double?
        )
    }

    data class Data(
        @SerializedName("clouds") val clouds: Clouds?,
        @SerializedName("dt") val dt: Int?,
        @SerializedName("dt_txt") val dtTxt: String?,
        @SerializedName("main") val main: Main?,
        @SerializedName("pop") val pop: Double?,
        @SerializedName("rain") val rain: Rain?,
        @SerializedName("sys") val sys: Sys?,
        @SerializedName("visibility") val visibility: Int?,
        @SerializedName("weather") val weather: List<Weather?>?,
        @SerializedName("wind") val wind: Wind?
    ) {
        data class Clouds(
            @SerializedName("all") val all: Int?
        )

        data class Main(
            @SerializedName("feels_like") val feelsLike: Double?,
            @SerializedName("grnd_level") val grndLevel: Int?,
            @SerializedName("humidity") val humidity: Int?,
            @SerializedName("pressure") val pressure: Int?,
            @SerializedName("sea_level") val seaLevel: Int?,
            @SerializedName("temp") val temp: Double?,
            @SerializedName("temp_kf") val tempKf: Double?,
            @SerializedName("temp_max") val tempMax: Double?,
            @SerializedName("temp_min") val tempMin: Double?
        )

        data class Rain(
            @SerializedName("3h") val h: Double?
        )

        data class Sys(
            @SerializedName("pod") val pod: String?
        )

        data class Weather(
            @SerializedName("description") val description: String?,
            @SerializedName("icon") val icon: String?,
            @SerializedName("id") val id: Int?,
            @SerializedName("main") val main: String?
        )

        data class Wind(
            @SerializedName("deg") val deg: Int?,
            @SerializedName("gust") val gust: Double?,
            @SerializedName("speed") val speed: Double?
        )
    }

    fun ForecastResponseApi_OpenWeather.toForecastWeatherData(): ForecastWeatherData {
        val forecastDetails = this.list?.map { data ->
            data.let {
                ForecastDetail(
                    date = it.dtTxt, // Date from the "dt_txt" field for each data point
                    temperature = it.main?.temp, // Current temperature
                    temperatureMin = it.main?.tempMin, // Minimum temperature
                    temperatureMax = it.main?.tempMax, // Maximum temperature
                    pressure = it.main?.pressure?.toDouble(), // Atmospheric pressure
                    humidity = it.main?.humidity, // Humidity percentage
                    windSpeed = it.wind?.speed, // Wind speed
                    windDirection = it.wind?.deg?.toString(), // Wind direction in degrees
                    icon = it.weather?.firstOrNull()?.icon, // Weather condition icon
                    description = it.weather?.firstOrNull()?.description, // Weather condition text description
                    hourlyDetails = listOf(
                        HourlyDetail(
                            time = it.dtTxt,
                            temp = it.main?.temp,
                            feelsLike = it.main?.feelsLike,
                            condition = it.weather?.firstOrNull()?.description,
                            precipitation = it.rain?.h
                        )
                    ),
                    dailyDetails = null
                )
            }
        } ?: listOf()

        return ForecastWeatherData(
            country = this.city?.country, // Country from the city object
            city = this.city?.name, // City name
            lat = this.city?.coord?.lat, // Latitude
            lon = this.city?.coord?.lon, // Longitude
            forecasts = forecastDetails
        )
    }
}