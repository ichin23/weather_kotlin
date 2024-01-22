package com.ichin23.weather.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ichin23.weather.R
import com.ichin23.weather.ui.theme.Blue
import java.time.LocalDateTime
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun  HomePage(
    viewModel: WeatherViewModel = hiltViewModel()
)  {

            Scaffold(
                containerColor = Blue
            ) {
                Box(Modifier.padding(it)){
                    Column {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 70.dp)) {
                            Column(Modifier
                                .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text =
                                "${viewModel.response?.listWeatherResponse?.get(0)?.weatherDataResponse?.temp?.toInt() ?: "--"}º"
                             ,
                                    fontSize = 70.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    viewModel.weather.city!!.name,
                                    fontSize=30.sp,
                                    color = Color.White
                                )
                            }
                        }
                        Box(modifier = Modifier.padding(20.dp)){
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.35F))

                            ){
                                if(viewModel.response?.listWeatherResponse != null){
                                    val climas = viewModel.response?.listWeatherResponse?.subList(1,viewModel.response?.listWeatherResponse?.lastIndex ?: -1 )
                                    if (climas!=null ) {

                                            LazyRow(
                                                Modifier.height(150.dp)
                                            ) {
                                                items(climas) {
                                                    Column(
                                                        Modifier
                                                            .height(150.dp)
                                                            .padding(8.dp),
                                                        verticalArrangement = Arrangement.SpaceAround
                                                    ) {
                                                        val hora = LocalDateTime.ofEpochSecond(it.dt,0, ZoneOffset.ofHours(
                                                            viewModel.response!!.timezone.timezone/36000))
                                                        Text(hora.dayOfWeek.name)
                                                        Text("${hora.hour}:${hora.minute}")
                                                        Text(
                                                            it.weatherDataResponse.temp.toInt()
                                                                .toString() + "ºC"
                                                        )
                                                        Text(
                                                            it.weatherDataResponse.humidity.toInt()
                                                                .toString() + "%"
                                                        )
                                                    }
                                                }
                                            }

                                        Text(viewModel.response?.listWeatherResponse?.size.toString())


                                    }
                                }
                            }
                        }
                    }
                }
            }
}
