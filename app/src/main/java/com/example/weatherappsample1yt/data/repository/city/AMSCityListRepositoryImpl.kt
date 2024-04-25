package com.example.weatherappsample1yt.data.repository.city

import com.example.weatherappsample1yt.data.api.client.ApiKeyProvider
import com.example.weatherappsample1yt.data.api.client.ApiProvider
import com.example.weatherappsample1yt.data.model.format.CityWeatherData
import com.example.weatherappsample1yt.data.repository.city.dataSource.CityListRemoteDataSource
import com.example.weatherappsample1yt.data.repository.city.dataSourceImpl.getAMSCityListRemoteDataSourceImpl
import com.example.weatherappsample1yt.domain.repository.CityRepository
import okhttp3.OkHttpClient

private class AMSCityListRepositoryImpl: CityRepository {
    private val apiKey = ApiKeyProvider.getApiKey(ApiProvider.METEO_STAT)
    private val client = OkHttpClient.Builder().addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.addHeader("X-RapidAPI-Key", apiKey)
        requestBuilder.addHeader(
            "X-RapidAPI-Host", "ai-weather-by-meteosource.p.rapidapi.com"
        )
        requestBuilder.addHeader(
            "Authorization",
            "Bearer BQCQavno6gAGixz1zrPD6ygXYXwiYxseLyenRep7v9hQP8-5qicpiyR9SNf1bwE5Ao3g7ZGX00FXfx0GLj5sWduflAycVYM4khRyIZYmcAVOefdbFq4obTXnnfok6JjWUCJsEzFt5bqSjpQGYDwhhhLii20jLCvGPfGYvw4AiqsYUnmWMoiGioF2SZim16qh1yzrPzuCQKeobdeLjsfsdqvGrnuiuwmSc0Joe-X20se7aoA3oA"
        )
        requestBuilder.addHeader(
            "Cookie", "HCLBSTICKY=7f1aab70544475f31df991214bd4e67c|Zidyw|Zidyr"
        )
        val request = requestBuilder.build()
        it.proceed(request)
    }.build()

    val cityListRemoteDataSource: CityListRemoteDataSource = getAMSCityListRemoteDataSourceImpl(client)
    override suspend fun getCitiesList(cityName: String, limit: Int): CityWeatherData? {
        return cityListRemoteDataSource.getCities(cityName, limit)
    }
}

fun getAMSCityListRepositoryImpl(): CityRepository {
    return AMSCityListRepositoryImpl()
}