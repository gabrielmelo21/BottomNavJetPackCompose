package com.melo.bottomnavjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.melo.bottomnavjetpackcompose.api.API_CALLS
import com.melo.bottomnavjetpackcompose.api.Alimentos
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.ui.theme.BottomNavJetPackComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            BottomNavJetPackComposeTheme {
                MainScreen()

  /**
  val apiService = ApiService()
  val alimentos: MutableState<List<Alimentos>> = mutableStateOf(emptyList())


                CoroutineScope(Dispatchers.Main).launch {
                    val alimentos = apiService.getAlimentos()
                    for (alimento in alimentos) {
                        println("Alimento: ${alimento.alimento}, Calorias: ${alimento.calorias}")
                    }
                }
*/







            }
        }
    }
}
