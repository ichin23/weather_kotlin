package com.ichin23.weather.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ichin23.weather.WeatherApplication
import com.ichin23.weather.data.local.WeatherService
import com.ichin23.weather.data.remote.dto.WeatherAPI
import com.ichin23.weather.data.remote.dto.WeatherResponse
import com.ichin23.weather.data.remote.dto.WeatherResponseList
import com.ichin23.weather.data.remote.dto.WeatherTokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

sealed interface WeatherUIState{
    data class Success(val data:Any): WeatherUIState
    object Error: WeatherUIState
    object Loading: WeatherUIState
}

@HiltViewModel
class WeatherViewModel @Inject constructor(private val service: WeatherAPI, val weather:WeatherService):ViewModel() {
    var token:WeatherTokenResponse? by mutableStateOf(null)
        private set

    var response: WeatherResponseList? by mutableStateOf(null)
        private set

    init {
        getData()
    }

    fun setNovoToken(novoToken:WeatherTokenResponse){
        token=novoToken
    }

    fun getData(){
        viewModelScope.launch {
            response= service.getForecastWeather(weather.city!!)
        }
    }
}
