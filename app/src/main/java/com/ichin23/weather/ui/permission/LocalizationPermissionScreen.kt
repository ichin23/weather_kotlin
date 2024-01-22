package com.ichin23.weather.ui.permission

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ichin23.weather.R
import com.ichin23.weather.ui.screens.WeatherViewModel
import com.ichin23.weather.util.Routes
import com.ichin23.weather.util.UiEvent
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalizationPermissionScreen( onNavigate: (UiEvent.Navigate)->Unit,  viewModel:LocalizationViewModel= hiltViewModel()) {

  val permissionState = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions()
  ){isGranted->
    val set = isGranted.values.toSet()
    if(set.size==1 && set.first()){
      onNavigate(UiEvent.Navigate(Routes.SEARCH_CITY))
    }
  }

  viewModel.getToken()
  val context = LocalContext.current

  if(ContextCompat.checkSelfPermission(
      context,
      Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&  ContextCompat.checkSelfPermission(
      context,
      Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED ){
    onNavigate(UiEvent.Navigate(Routes.SEARCH_CITY))
  }



  Scaffold(
    modifier= Modifier
      .fillMaxSize(),
    containerColor = (MaterialTheme.colorScheme.primary)
  ) {padding->
    Column( modifier= Modifier
      .fillMaxSize()
      .padding(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceAround,
      ) {
      Image(
        modifier= Modifier.width(200.dp),
        painter = painterResource(id = R.drawable.nuvem),
        contentDescription = "Nuvem"
      )

      Column(
        modifier= Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ){
        Text(
          "Precisamos de acesso a sua localização para melhor informar o clima",
          fontSize = 20.sp,
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold
        )

        Box(Modifier.height(12.dp))

        TextButton(
          modifier = Modifier.padding((5.dp)),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
          ),
          onClick = {
            permissionState.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))

          }
        ) {
          Text(
            "Conceder",
            fontSize = 25.sp,
            color = Color.Gray,
            fontWeight = FontWeight.ExtraBold
          )
        }
      }
    }
  }
}


