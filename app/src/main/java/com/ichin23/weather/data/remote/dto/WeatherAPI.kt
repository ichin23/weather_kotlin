package com.ichin23.weather.data.remote.dto

import androidx.lifecycle.LiveData
import com.ichin23.weather.data.remote.WeatherAPIImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.forms.FormDataContent
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface WeatherAPI{
    suspend fun getToken(): WeatherTokenResponse?
    suspend fun getWeather(city: City): WeatherResponse?

    suspend fun getForecastWeather(city:City): WeatherResponseList?
    suspend fun getCities(request: String, token: String): CitiesResponse?
}