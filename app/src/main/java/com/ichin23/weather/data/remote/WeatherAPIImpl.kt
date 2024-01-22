package com.ichin23.weather.data.remote

import com.ichin23.weather.data.remote.dto.CitiesResponse
import com.ichin23.weather.data.remote.dto.City
import com.ichin23.weather.data.remote.dto.WeatherAPI
import com.ichin23.weather.data.remote.dto.WeatherResponse
import com.ichin23.weather.data.remote.dto.WeatherResponseList
import com.ichin23.weather.data.remote.dto.WeatherTokenRequest
import com.ichin23.weather.data.remote.dto.WeatherTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.Attributes

class WeatherAPIImpl(
    private val client: HttpClient
) :  WeatherAPI {

    override suspend fun getToken(): WeatherTokenResponse? {
        return try {
             client.post {
                url(HttpRoutes.accessToken)
                body = FormDataContent(
                    Parameters.build {
                        append("grant_type" , "client_credentials")
                        append("client_id" , "ZEeI2N3JOelEhAewdKMWbDViOkj1JNuJ")
                        append("client_secret" , "Op8Nrp8NubBxzGjb")
                    }
                )
            }
        }catch(e: ClientRequestException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: ServerResponseException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: Exception){
            println("ERROR: ${e.message}")
            return null
        }
    }

    override suspend fun getCities(request: String, token:String) : CitiesResponse? {
        return try {
            client.get {
                url(HttpRoutes.citySearch)
                headers { header("Authorization", "Bearer $token") }
                parameter("keyword", request)
            }
        }catch(e: ClientRequestException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: ServerResponseException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: Exception){
            println("ERROR: ${e.message}")
            return null
        }
    }

    override suspend fun getWeather(city:City): WeatherResponse? {
        return try{
            client.get {
                url(HttpRoutes.currentWeather)
                parameter("appid", "0f4418d3b7ea35eca95067561eeb145b")
                parameter("lat", city.geoCode["latitude"])
                parameter("lon", city.geoCode["longitude"])
                parameter("units", "metric")
            }
        }catch(e: ClientRequestException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: ServerResponseException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: Exception){
            println("ERROR: ${e.message}")
            return null
        }
    }

    override suspend fun getForecastWeather(city:City): WeatherResponseList? {
        return try{
            client.get {
                url(HttpRoutes.forecastWeather)
                parameter("appid", "0f4418d3b7ea35eca95067561eeb145b")
                parameter("lat", city.geoCode["latitude"])
                parameter("lon", city.geoCode["longitude"])
                parameter("units", "metric")
                parameter("cnt", 20)
            }
        }catch(e: ClientRequestException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: ServerResponseException){
            println("ERROR: ${e.response.status.description}")
            return null
        }catch(e: Exception){
            println("ERROR: ${e.message}")
            return null
        }
    }
}