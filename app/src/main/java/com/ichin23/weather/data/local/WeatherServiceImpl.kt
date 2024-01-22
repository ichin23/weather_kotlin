package com.ichin23.weather.data.local

import com.ichin23.weather.data.remote.dto.City
import com.ichin23.weather.data.remote.dto.WeatherTokenResponse

class WeatherServiceImpl :WeatherService{
    override var city: City? = null
    override var token: WeatherTokenResponse?=null
}