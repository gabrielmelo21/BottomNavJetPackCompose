package com.melo.bottomnavjetpackcompose.api

import android.util.Log
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.api.dataClasses.AlimentosIngeridos
import com.melo.bottomnavjetpackcompose.api.dataClasses.Calorias
import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import com.melo.bottomnavjetpackcompose.bars.BottomBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    // private val BASE_URL = "http://192.168.1.100:8081/api/"
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


    suspend fun getAlimentosIngeridos(): List<AlimentosIngeridos> {
        return withContext(Dispatchers.IO) {
            try {
                val alimentosIngeridos = api.getAlimentosIngeridos().await()
                for (food in alimentosIngeridos) {
                    Log.d("Teste", food.alimento)
                }
                alimentosIngeridos
            } catch (e: Exception) {
                // Handle errors here
                Log.e("ApiService", "Error: ${e.message}")
                emptyList()
            }
        }
    }


    suspend fun getCalorias(): List<Calorias>{
        return withContext(Dispatchers.IO){
            try {
                val calorias = api.getCalorias().await()

                for (cal in calorias){
                    Log.d("Calorias-ApiService", "${cal.calorias_atual} - ${cal.tmb} - ${cal.deficit_calorico} - ${cal.data_dia}")
                }
                calorias

            }catch (e : Exception){
                Log.e("ApiService", "Error: ${e.message}")
            } as List<Calorias>
        }
    }


    suspend fun getPeso(): List<Peso>{
         return withContext(Dispatchers.IO){
              try {
                  val peso = api.getPeso().await()
                  for (x in peso) {
                      Log.d("Teste", x.peso.toString())
                  }
                 peso
              }catch (e : Exception){
                  Log.e("ApiService", "Error: ${e.message}")
         }
         } as List<Peso>
    }



    suspend fun deletarAlimentoIngerido(id: Int): Boolean {
        return try {
            api.deletarAlimentoIngerido(id)
            true // Exclusão bem-sucedida
        } catch (e: Exception) {
            // Handle errors here
            Log.e("ApiService", "Error: ${e.message}")
            false // Exclusão falhou
        }
    }

    suspend fun deletarAlimento(id: Int): Boolean {
        return try {
            api.deletarAlimento(id)
            true // Exclusão bem-sucedida
        } catch (e: Exception) {
            // Handle errors here
            Log.e("ApiService", "Error: ${e.message}")
            false // Exclusão falhou
        }
    }

    suspend fun updateCaloria(calorias: Calorias): Boolean{
        return try {
            api.updateCalorias(calorias)
            true
        } catch (e: Exception) {
            Log.e("ApiService", "Error: ${e.message}")
            false
        }
    }



    suspend fun addAlimentos(alimentos: Alimentos): Boolean {
        return try {
                api.adicionarAlimento(alimentos)
                true
            } catch (e: Exception){
                Log.e("ApiService", "Error: ${e.message}" )
               false
            }

    }
    suspend fun addAlimentosIngeridos(alimentosIngeridos: AlimentosIngeridos): Boolean {
        return try {
            api.adicionarAlimentosIngerido(alimentosIngeridos)
            true
        } catch (e: Exception){
            Log.e("ApiService", "Error: ${e.message}" )
            false
        }

    }








}
