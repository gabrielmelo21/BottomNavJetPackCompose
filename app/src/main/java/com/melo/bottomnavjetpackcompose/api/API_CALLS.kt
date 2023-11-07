package com.melo.bottomnavjetpackcompose.api


import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.api.dataClasses.AlimentosIngeridos
import com.melo.bottomnavjetpackcompose.api.dataClasses.Calorias
import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface API_CALLS {
    @POST("addAlimentos")
    suspend fun adicionarAlimento(@Body alimento: Alimentos): Response<Alimentos>

    @POST("addAlimentosIngeridos")
    suspend fun adicionarAlimentosIngerido(@Body alimentosIngeridos: AlimentosIngeridos): Response<AlimentosIngeridos>

    @GET("alimentos")
    fun getAlimentos(): Call<List<Alimentos>>

   @GET("calorias")
   fun getCalorias(): Call<List<Calorias>>

    @GET("peso")
    fun getPeso(): Call<List<Peso>>

   @GET("listarAlimentosIngeridos")
   fun getAlimentosIngeridos() : Call<List<AlimentosIngeridos>>

    @DELETE("deletarAlimentosIngeridos/{id}")
    suspend fun deletarAlimentoIngerido(@Path("id") id: Int)

    @DELETE("deletarAlimento/{id}")
    suspend fun deletarAlimento(@Path("id") id: Int)

   @PUT("updateCalorias/202")
   suspend fun updateCalorias(@Body calorias: Calorias): Response<Calorias>

}