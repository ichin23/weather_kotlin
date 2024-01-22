package com.ichin23.weather.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichin23.weather.data.local.WeatherService
import com.ichin23.weather.data.remote.dto.City
import com.ichin23.weather.data.remote.dto.WeatherAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (private val service: WeatherAPI, private val weather:WeatherService,  savedStateHandle: SavedStateHandle): ViewModel() {
    var text by  mutableStateOf("")
    var cities by mutableStateOf(emptyList<City>())
        private set
    var loading by mutableStateOf(false)
    var city by mutableStateOf<City?>(null )

    fun getCities(newText:String){
        text = newText
        if(text.length<3) {
            cities= emptyList()
            return
        }
        viewModelScope.launch {
            val result = service.getCities(text, weather.token!!.access_token)
            cities = ((result?.list ?: emptyList<City>()))
        }
    }

    fun selectCity(city: City){
        this.city = city
        weather.city=city
        text=""
        cities  = emptyList()
    }
}