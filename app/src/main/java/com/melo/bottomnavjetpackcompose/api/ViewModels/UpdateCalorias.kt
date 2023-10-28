package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Calorias
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateCalorias : ViewModel() {
    private val apiService = ApiService()

    suspend fun updateCalorias(calorias: Calorias){
        return withContext(Dispatchers.IO){
              try {
                 apiService.updateCaloria(calorias)
              }catch (e: Exception){
                  Log.e("CaloriasUpdate", "Erro: ${e.message}")
              }
        }
    }
}