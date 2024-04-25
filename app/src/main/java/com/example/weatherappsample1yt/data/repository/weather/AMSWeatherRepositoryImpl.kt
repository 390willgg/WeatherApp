package com.example.weatherappsample1yt.data.repository.weather

import android.util.Log
import com.example.weatherappsample1yt.data.api.client.ApiKeyProvider
import com.example.weatherappsample1yt.data.api.client.ApiProvider
import com.example.weatherappsample1yt.data.model.format.CurrentWeatherData
import com.example.weatherappsample1yt.data.model.format.ForecastWeatherData
import com.example.weatherappsample1yt.data.repository.weather.dataSource.WeatherRemoteDataSource
import com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl.getAMSWeatherRemoteDataSourceImpl
import com.example.weatherappsample1yt.domain.repository.WeatherRepository
import okhttp3.OkHttpClient

private class AMSWeatherRepositoryImpl : WeatherRepository {
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

    val weatherRemoteDataSource: WeatherRemoteDataSource = getAMSWeatherRemoteDataSourceImpl(client)
    override suspend fun getCurrentWeatherData(
        lat: Double,
        lon: Double,
        units: String
    ): CurrentWeatherData? {
        Log.d("Repository", "Using AMSWeatherRepository for getCurrentWeatherData")
        return weatherRemoteDataSource.getCurrentWeather(lat, lon, units)
    }

    override suspend fun getForecastWeatherData(
        lat: Double,
        lon: Double,
        units: String
    ): ForecastWeatherData? {
        Log.d("Repository", "Using AMSWeatherRepository for getForecastWeatherData")
        return weatherRemoteDataSource.getForecast(lat, lon, units)
    }

}

fun getAMSWeatherRepositoryImpl(): WeatherRepository  {
    Log.d("Repository", "Creating AMSWeatherRepository")
    return AMSWeatherRepositoryImpl()
}
