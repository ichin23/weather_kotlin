package com.ichin23.weather.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ichin23.weather.R
import com.ichin23.weather.data.remote.dto.CitiesResponse
import com.ichin23.weather.data.remote.dto.City
import com.ichin23.weather.data.remote.dto.WeatherAPI
import com.ichin23.weather.data.remote.dto.WeatherTokenRequest
import com.ichin23.weather.data.remote.dto.WeatherTokenResponse
import com.ichin23.weather.util.Routes
import com.ichin23.weather.util.UiEvent
import io.ktor.utils.io.printStack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityScreen (onNavigate: (UiEvent.Navigate)->Unit, viewModel: SearchViewModel = hiltViewModel()){

    Scaffold {
        Surface (modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            Column {
                Box(Modifier.padding(8.dp)) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
                        value = viewModel.text,
                        onValueChange = { newText -> viewModel.getCities(newText) },
                        label = { Text("Busque sua cidade") },
                        colors = TextFieldDefaults.textFieldColors(
                            placeholderColor = Color.Transparent
                        )
                    )
                }
                if(viewModel.city!=null && viewModel.cities.isEmpty()){
                    CityComp(city = viewModel.city!!, viewModel)
                    Button(onClick = {
                                     onNavigate(UiEvent.Navigate(Routes.HOME))
                    },
                        modifier=Modifier.fillMaxWidth()) {
                        Text("Avançar")
                    }
                }else{
            LazyColumn(
                Modifier.fillMaxWidth()
            ){
                items(viewModel.cities){
                    CityComp(city = it, viewModel, Modifier.clickable { viewModel.selectCity(it) })
                }
            }

        }}}
    }
}

@Composable

fun CityComp(city:City, viewModel: SearchViewModel, modifier: Modifier = Modifier){
    Box (
        modifier
            .fillMaxWidth()
            .padding(8.dp, 14.dp)

    ){
        Row {
            if(city==viewModel.city){
                Image(painter = painterResource(id = R.drawable.location), contentDescription = null)
            }
            Text("${city.name} - ${city.address["stateCode"] ?: city.address["countryCode"]}")
        }
    }
}