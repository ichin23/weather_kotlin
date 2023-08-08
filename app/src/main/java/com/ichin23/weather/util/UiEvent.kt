package com.ichin23.weather.util

sealed class UiEvent {
  data class Navigate(val route:String): UiEvent()
  object PopBackStack: UiEvent()
}
