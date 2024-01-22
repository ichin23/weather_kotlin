package com.ichin23.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@Serializable
data class CitiesResponse(
    @SerialName("data")
    val list: List<City>
)

@Serializable
data class City(
    val name:String,
    val address: Map<String, String>,
    val geoCode: Map<String, Float>
)
