package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Calorias
import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdatePeso : ViewModel() {
    private val apiService = ApiService()

    suspend fun updatePeso(peso: Peso){
        return withContext(Dispatchers.IO){
            try {
                apiService.updatePeso(peso)
            }catch (e: Exception){
                Log.e("CaloriasUpdate", "Erro: ${e.message}")
            }
        }
    }
}