package com.melo.bottomnavjetpackcompose.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiService {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val BASE_URL = "http://10.0.2.2:8081/api/"
    private val api: API_CALLS = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API_CALLS::class.java)

    suspend fun getAlimentos(): List<Alimentos> {
        return withContext(Dispatchers.IO) {
            try {
                val alimentos = api.getAlimentos().await()
                for (food in alimentos) {
                    Log.d("Teste", food.alimento)
                }
                alimentos
            } catch (e: Exception) {
                // Handle errors here
                Log.e("ApiService", "Error: ${e.message}")
                emptyList()
            }
        }
    }
}
