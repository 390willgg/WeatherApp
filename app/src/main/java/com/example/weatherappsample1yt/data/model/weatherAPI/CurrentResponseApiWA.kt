package com.example.weatherappsample1yt.data.model.weatherAPI


import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.google.gson.annotations.SerializedName

data class CurrentResponseApiWA(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("location")
    val location: Location?
) {
    data class Current(
        @SerializedName("cloud")
        val cloud: Int?,
        @SerializedName("condition")
        val condition: Condition?,
        @SerializedName("feelslike_c")
        val feelslikeC: Double?,
        @SerializedName("feelslike_f")
        val feelslikeF: Double?,
        @SerializedName("gust_kph")
        val gustKph: Double?,
        @SerializedName("gust_mph")
        val gustMph: Double?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("is_day")
        val isDay: Int?,
        @SerializedName("last_updated")
        val lastUpdated: String?,
        @SerializedName("last_updated_epoch")
        val lastUpdatedEpoch: Int?,
        @SerializedName("precip_in")
        val precipIn: Double?,
        @SerializedName("precip_mm")
        val precipMm: Double?,
        @SerializedName("pressure_in")
        val pressureIn: Double?,
        @SerializedName("pressure_mb")
        val pressureMb: Int?,
        @SerializedName("temp_c")
        val tempC: Int?,
        @SerializedName("temp_f")
        val tempF: Double?,
        @SerializedName("uv")
        val uv: Int?,
        @SerializedName("vis_km")
        val visKm: Int?,
        @SerializedName("vis_miles")
        val visMiles: Int?,
        @SerializedName("wind_degree")
        val windDegree: Int?,
        @SerializedName("wind_dir")
        val windDir: String?,
        @SerializedName("wind_kph")
        val windKph: Double?,
        @SerializedName("wind_mph")
        val windMph: Double?
    ) {
        data class Condition(
            @SerializedName("code")
            val code: Int?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("text")
            val text: String?
        )
    }

    data class Location(
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("localtime")
        val localtime: String?,
        @SerializedName("localtime_epoch")
        val localtimeEpoch: Int?,
        @SerializedName("lon")
        val lon: Double?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("tz_id")
        val tzId: String?
    )

    fun toCurrentWeatherData(): CurrentWeatherData {
        return CurrentWeatherData(
            city = location?.name ?: "",
            country = location?.country ?: "",
            latitude = location?.lat ?: 0.0,
            longitude = location?.lon ?: 0.0,
            temperature = current?.feelslikeC ?: 0.0,
            maxTemperature = current?.tempC?.toDouble() ?: 0.0,
            minTemperature = current?.tempC?.toDouble() ?: 0.0,
            weatherStatus = current?.condition?.text ?: "",
            weatherDescription = current?.condition?.text ?: "",
            weatherIcon = current?.condition?.icon ?: "",
            windSpeed = current?.windKph ?: 0.0,
            humidity = current?.humidity ?: 0,
            icon = current?.condition?.icon ?: "",
            precipitation = current?.precipMm?.toDouble() ?: 0.0
        )
    }
}