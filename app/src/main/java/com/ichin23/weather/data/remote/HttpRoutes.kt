package com.ichin23.weather.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://test.api.amadeus.com/v1"
    const val accessToken = "$BASE_URL/security/oauth2/token"
    const val citySearch = "$BASE_URL//reference-data/locations/cities"
    const val currentWeather = "https://api.openweathermap.org/data/2.5/current"
    const val forecastWeather = "https://api.openweathermap.org/data/2.5/forecast"
}