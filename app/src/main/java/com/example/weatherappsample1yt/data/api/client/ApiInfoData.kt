package com.example.weatherappsample1yt.data.api.client

object ApiInfoData {
    val apiInfo = mapOf(
        ApiProvider.OPEN_WEATHER to ApiInfo(
            "https://api.openweathermap.org/",
            mapOf("Accept" to "application/json")
        ),

        ApiProvider.WEATHER_API to ApiInfo(
            "https://weatherapi-com.p.rapidapi.com/",
            mapOf(
                "X-RapidAPI-Key" to ApiKeyProvider.getApiKey(ApiProvider.WEATHER_API),
                "X-RapidAPI-Host" to "weatherapi-com.p.rapidapi.com"
            )
        ),

        ApiProvider.METEO_STAT to ApiInfo(
            "https://ai-weather-by-meteosource.p.rapidapi.com/",
            mapOf(
                "X-RapidAPI-Key" to ApiKeyProvider.getApiKey(ApiProvider.METEO_STAT),
                "X-RapidAPI-Host" to "ai-weather-by-meteosource.p.rapidapi.com"
            )
        )
    )
}