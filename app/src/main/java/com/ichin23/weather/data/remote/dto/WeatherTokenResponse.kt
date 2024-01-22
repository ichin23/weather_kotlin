package com.ichin23.weather.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherTokenResponse (
    val username: String,
    val token_type: String,
    val access_token: String,
    val expires_in: Int
)