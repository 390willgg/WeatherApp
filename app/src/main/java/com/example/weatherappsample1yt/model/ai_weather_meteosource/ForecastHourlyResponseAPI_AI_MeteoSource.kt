package com.example.weatherappsample1yt.model.ai_weather_meteosource


import com.example.weatherappsample1yt.model.format.DailyDetail
import com.example.weatherappsample1yt.model.format.ForecastDetail
import com.example.weatherappsample1yt.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.model.format.HourlyDetail
import com.example.weatherappsample1yt.model.weather_api.ForecastResponseAPI_WeatherAPI
import com.google.gson.annotations.SerializedName

data class ForecastHourlyResponseAPI_AI_MeteoSource(
    @SerializedName("elevation")
    val elevation: Int?,
    @SerializedName("hourly")
    val hourly: Hourly?,
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lon")
    val lon: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("units")
    val units: String?
) {
    data class Hourly(
        @SerializedName("data")
        val `data`: List<Data?>?
    ) {
        data class Data(
            @SerializedName("cloud_cover")
            val cloudCover: Int?,
            @SerializedName("date")
            val date: String?,
            @SerializedName("dew_point")
            val dewPoint: Double?,
            @SerializedName("feels_like")
            val feelsLike: Double?,
            @SerializedName("humidity")
            val humidity: Int?,
            @SerializedName("icon")
            val icon: Int?,
            @SerializedName("ozone")
            val ozone: Double?,
            @SerializedName("precipitation")
            val precipitation: Precipitation?,
            @SerializedName("pressure")
            val pressure: Double?,
            @SerializedName("probability")
            val probability: Probability?,
            @SerializedName("summary")
            val summary: String?,
            @SerializedName("temperature")
            val temperature: Double?,
            @SerializedName("uv_index")
            val uvIndex: Double?,
            @SerializedName("visibility")
            val visibility: Double?,
            @SerializedName("weather")
            val weather: String?,
            @SerializedName("wind")
            val wind: Wind?,
            @SerializedName("wind_chill")
            val windChill: Double?
        ) {
            data class Precipitation(
                @SerializedName("total")
                val total: Int?,
                @SerializedName("type")
                val type: String?
            )

            data class Probability(
                @SerializedName("freeze")
                val freeze: Int?,
                @SerializedName("precipitation")
                val precipitation: Int?,
                @SerializedName("storm")
                val storm: Int?
            )

            data class Wind(
                @SerializedName("angle")
                val angle: Int?,
                @SerializedName("dir")
                val dir: String?,
                @SerializedName("gusts")
                val gusts: Double?,
                @SerializedName("speed")
                val speed: Double?
            )
        }
    }

    fun ForecastResponseAPI_WeatherAPI.toForecastWeatherData(): ForecastWeatherData {
        val forecastDetails = this.forecast?.forecastday?.mapNotNull { forecastday ->
            forecastday?.let { fd ->
                ForecastDetail(
                    date = fd.date,
                    temperature = fd.day?.avgtempC,
                    temperatureMin = fd.day?.mintempC,
                    temperatureMax = fd.day?.maxtempC,
                    pressure = fd.day?.totalprecipMm?.toDouble(),
                    humidity = fd.day?.avghumidity,
                    windSpeed = fd.day?.maxwindKph,
                    windDirection = fd.day?.condition?.text,
                    icon = fd.day?.condition?.icon,
                    description = fd.day?.condition?.text,
                    dailyDetails = listOf(
                        DailyDetail(
                            date = fd.date,
                            maxTemp = fd.day?.maxtempC,
                            minTemp = fd.day?.mintempC,
                            condition = fd.day?.condition?.text,
                            precipitation = fd.day?.totalprecipMm
                        )
                    ),
                    hourlyDetails = fd.hour?.mapNotNull { hour ->
                        hour?.let {
                            HourlyDetail(
                                time = it.time,
                                temp = it.tempC,
                                feelsLike = it.feelslikeC,
                                condition = it.condition?.text,
                                precipitation = it.precipMm
                            )
                        }
                    }
                )
            }
        } ?: listOf()

        return ForecastWeatherData(
            country = this.location?.country,
            city = this.location?.name,
            lat = this.location?.lat,
            lon = this.location?.lon,
            forecasts = forecastDetails
        )
    }

}