package com.example.weatherappsample1yt.model.ai_weather_meteosource


import com.google.gson.annotations.SerializedName

data class CurrentResponseAPI_AI_MeteoSource(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("elevation")
    val elevation: Int?,
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lon")
    val lon: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("units")
    val units: String?
) {
    data class Current(
        @SerializedName("cloud_cover")
        val cloudCover: Int?,
        @SerializedName("dew_point")
        val dewPoint: Double?,
        @SerializedName("feels_like")
        val feelsLike: Double?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("icon")
        val icon: String?,
        @SerializedName("icon_num")
        val iconNum: Int?,
        @SerializedName("ozone")
        val ozone: Double?,
        @SerializedName("precipitation")
        val precipitation: Precipitation?,
        @SerializedName("pressure")
        val pressure: Double?,
        @SerializedName("summary")
        val summary: String?,
        @SerializedName("temperature")
        val temperature: Double?,
        @SerializedName("uv_index")
        val uvIndex: Int?,
        @SerializedName("visibility")
        val visibility: Int?,
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