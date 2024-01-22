package com.ichin23.weather.ui.permission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ichin23.weather.data.local.WeatherService
import com.ichin23.weather.data.remote.dto.WeatherAPI
import com.ichin23.weather.ui.screens.WeatherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalizationViewModel @Inject constructor(private val service: WeatherAPI, private val weather:WeatherService) : ViewModel(){
    fun getToken(){
        viewModelScope.launch {
            val token = service.getToken()
            if(token!=null)
                weather.token=token
        }
    }
}