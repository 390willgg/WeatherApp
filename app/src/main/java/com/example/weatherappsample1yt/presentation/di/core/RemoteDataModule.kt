package com.example.weatherappsample1yt.presentation.di.core

import com.example.weatherappsample1yt.data.repository.city.dataSource.CityListRemoteDataSource
import com.example.weatherappsample1yt.data.repository.city.dataSourceImpl.getAMSCityListRemoteDataSourceImpl
import com.example.weatherappsample1yt.data.repository.city.dataSourceImpl.getOWCityListRemoteDataSourceImpl
import com.example.weatherappsample1yt.data.repository.city.dataSourceImpl.getWACityListRemoteDataSourceImpl
import com.example.weatherappsample1yt.data.repository.weather.dataSource.WeatherRemoteDataSource
import com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl.getAMSWeatherRemoteDataSourceImpl
import com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl.getOWWeatherRemoteDataSourceImpl
import com.example.weatherappsample1yt.data.repository.weather.dataSourceImpl.getWAWeatherRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideOWWeatherRemoteDataSource(
        client: OkHttpClient
    ): WeatherRemoteDataSource {
        return getOWWeatherRemoteDataSourceImpl(client)
    }

    @Singleton
    @Provides
    fun provideWAWeatherRemoteDataSource(
        client: OkHttpClient
    ): WeatherRemoteDataSource {
        return getWAWeatherRemoteDataSourceImpl(client)
    }

    @Singleton
    @Provides
    fun provideAMSWeatherRemoteDataSource(
        client: OkHttpClient
    ): WeatherRemoteDataSource {
        return getAMSWeatherRemoteDataSourceImpl(client)
    }

    @Singleton
    @Provides
    fun provideOWCityListRemoteDataSource(
        client: OkHttpClient
    ): CityListRemoteDataSource {
        return getOWCityListRemoteDataSourceImpl(client)
    }

    @Singleton
    @Provides
    fun provideWACityListRemoteDataSource(
        client: OkHttpClient
    ): CityListRemoteDataSource {
        return getWACityListRemoteDataSourceImpl( client)
    }

    @Singleton
    @Provides
    fun provideAMSCityListRemoteDataSource(
        client: OkHttpClient
    ): CityListRemoteDataSource {
        return getAMSCityListRemoteDataSourceImpl(client)
    }
}