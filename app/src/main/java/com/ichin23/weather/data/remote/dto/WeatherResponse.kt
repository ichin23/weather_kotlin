package com.ichin23.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherResponseList(
    @SerialName("list")
    val listWeatherResponse: List<WeatherResponse>,
    @SerialName("city")
    val timezone: Timezone
)
@Serializable
data class Timezone(
    val timezone: Int
)
@Serializable
data class WeatherResponse(
    val dt: Long,
    val dt_txt:String,
    @SerialName("weather")
    val generalResponse: List<WeatherGeneralResponse>,
    @SerialName("main")
    val weatherDataResponse: WeatherDataResponse,
    val visibility: Float
)

@Serializable
data class WeatherGeneralResponse(

    val main:String,
    val description:String,
    val icon:String
)

@Serializable
data class WeatherDataResponse(
    val temp: Float,
    val feels_like: Float,
    val pressure: Float,
    val humidity: Float

)