package com.ichin23.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ichin23.weather.data.remote.WeatherAPIImpl
import com.ichin23.weather.data.remote.dto.WeatherAPI
import com.ichin23.weather.data.remote.dto.WeatherTokenRequest
import com.ichin23.weather.data.remote.dto.WeatherTokenResponse
import com.ichin23.weather.ui.permission.LocalizationPermissionScreen
import com.ichin23.weather.ui.screens.HomePage
import com.ichin23.weather.ui.screens.SearchCityScreen
import com.ichin23.weather.ui.theme.WeatherTheme
import com.ichin23.weather.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      WeatherTheme {
        val navCont = rememberNavController()
        NavHost(navController = navCont, startDestination =Routes.PERMISSION_SCREEN){
          composable(Routes.PERMISSION_SCREEN){
            LocalizationPermissionScreen(onNavigate= {navCont.navigate(it.route)})
          }
          composable(Routes.HOME){
            HomePage()
          }
          composable(Routes.SEARCH_CITY){
            SearchCityScreen({navCont.navigate(it.route){
              launchSingleTop=true
            } })
          }
        }
      }
    }
  }
}
