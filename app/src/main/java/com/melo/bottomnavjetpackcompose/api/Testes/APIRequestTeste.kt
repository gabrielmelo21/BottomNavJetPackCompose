package com.melo.bottomnavjetpackcompose.api.Testes

import android.util.Log
import com.melo.bottomnavjetpackcompose.api.API_CALLS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

fun main(){

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val BASE_URL = "http://10.0.2.2:8081/api/"
    val TAG: String = "CHECK_RESPONSE5"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API_CALLS::class.java)

    GlobalScope.launch(Dispatchers.IO) {
        val alimentos = api.getAlimentos().await()
        for (food in alimentos){
             Log.d("Teste", food.alimento)
        }
    }

}