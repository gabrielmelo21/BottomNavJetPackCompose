package com.melo.bottomnavjetpackcompose.api

import retrofit2.Call
import retrofit2.http.GET

interface API_CALLS {

    @GET("alimentos")
    fun getAlimentos(): Call<List<Alimentos>>


}