package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.AlimentosIngeridos
import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddPesoViewModel :ViewModel() {
    private val apiService = ApiService()

    suspend fun AddPeso(peso: Peso){
        return withContext(Dispatchers.IO){
            try {
                apiService.addPeso(peso)
            }catch (e: Exception){
                Log.e("addPeso", "${e.message}")
            }
        }

    }
}