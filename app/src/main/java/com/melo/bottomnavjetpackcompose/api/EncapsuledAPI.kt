package com.melo.bottomnavjetpackcompose.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/*
Oque eu gostaria de fazer é: Fazer o HTTP REQUEST uma vez e salvar as informações em Cache
Porem eu preciso garantia da atualização async disso
no caso é atualizar o cache sempre que tiver uma nova atualização no banco
tanto quando eu enviar addos quanto receber dados


 */

fun main(){

}

@Composable
 fun RetrofitAPI(){

     val interceptor = HttpLoggingInterceptor()
     interceptor.level = HttpLoggingInterceptor.Level.BODY

     val client = OkHttpClient.Builder()
         .addInterceptor(interceptor)
         .build()

     val alimentosState = remember { mutableStateOf(emptyList<Alimentos>()) }
     val BASE_URL = "http://10.0.2.2:8081/api/"
     val TAG: String = "CHECK_RESPONSE5"
     val api = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .client(client)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         .create(API_CALLS::class.java)


     // Fazer a chamada à API
     api.getAlimentos().enqueue(object : Callback<List<Alimentos>> {
         override fun onResponse(call: Call<List<Alimentos>>, response: Response<List<Alimentos>>) {
             if (response.isSuccessful) {
                 val alimentos = response.body()
                 alimentos?.let {
                     // Atualizar o estado com a lista de alimentos
                     alimentosState.value = it

                     // Imprimir os dados no log usando Log.i
                     for (alimento in alimentos) {
                         Log.i(TAG, "Alimento: ${alimento.alimento}, Calorias: ${alimento.calorias}")
                     }
                 }
             } else {
                 Log.e(TAG, "Erro na resposta da API: ${response.code()}")
             }
         }

         override fun onFailure(call: Call<List<Alimentos>>, t: Throwable) {
             Log.e(TAG, "Erro na chamada da API: ${t.message}")
         }
     })


 }
