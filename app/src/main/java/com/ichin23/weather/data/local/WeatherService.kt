package com.ichin23.weather.data.local

import com.ichin23.weather.data.remote.dto.City
import com.ichin23.weather.data.remote.dto.WeatherTokenResponse

interface WeatherService {
    var city:City?
    var token: WeatherTokenResponse?
}