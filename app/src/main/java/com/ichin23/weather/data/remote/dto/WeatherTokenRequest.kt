package com.ichin23.weather.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherTokenRequest (
    val grant_type: String,
    val client_id: String,
    val client_secret: String
){
    fun toJson(): Map<String, String>{
        return mapOf(
            "grant_type" to grant_type,
            "client_id" to client_id,
            "client_secret" to client_secret,
            )
    }
}