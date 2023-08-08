package com.ichin23.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ichin23.weather.ui.permission.LocalizationPermissionScreen
import com.ichin23.weather.ui.theme.WeatherTheme
import com.ichin23.weather.util.Routes

class MainActivity : ComponentActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      WeatherTheme {
        val navCont = rememberNavController()
        NavHost(navController = navCont, startDestination =Routes.PERMISSION_SCREEN){
          composable(Routes.PERMISSION_SCREEN){
            LocalizationPermissionScreen(onNavigate= {navCont.clearBackStack(it.route)})
          }
          composable(Routes.HOME){
            Text("HOME")
          }
        }

      }
    }
  }


}
