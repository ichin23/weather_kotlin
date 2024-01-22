package com.ichin23.weather.di

import com.ichin23.weather.data.local.WeatherService
import com.ichin23.weather.data.local.WeatherServiceImpl
import com.ichin23.weather.data.remote.WeatherAPIImpl
import com.ichin23.weather.data.remote.dto.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttp():HttpClient{
        return HttpClient(Android){
            install(Logging){
                level= LogLevel.BODY
            }
            install(JsonFeature){
                serializer = KotlinxSerializer(
                    Json { ignoreUnknownKeys = true }
                )

            }
        }
    }
    @Provides
    @Singleton
    fun provideWeatherAPIs(client: HttpClient):WeatherAPI{
        return WeatherAPIImpl(client)
    }

    @Provides
    @Singleton
    fun provideWeatherService():WeatherService{
        return WeatherServiceImpl()
    }
}