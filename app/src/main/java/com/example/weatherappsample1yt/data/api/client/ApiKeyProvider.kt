package com.example.weatherappsample1yt.data.api.client

object ApiKeyProvider {
    private val apiKeys = mapOf(
        ApiProvider.OPEN_WEATHER to "49cac901dfcef84ab95c1c3d792d9a04",
        ApiProvider.WEATHER_API to "2ecdf6cfe5msh105a2f4e752782fp12c102jsnfc8c7fda92cc",
        ApiProvider.METEO_STAT to "2ecdf6cfe5msh105a2f4e752782fp12c102jsnfc8c7fda92cc"
    )

    fun getApiKey(provider: ApiProvider): String {
        return apiKeys[provider] ?: throw IllegalArgumentException("Unknown API provider: $provider")
    }
}

enum class ApiProvider {
    OPEN_WEATHER,
    WEATHER_API,
    METEO_STAT
}