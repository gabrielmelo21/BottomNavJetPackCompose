package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos

class AlimentosViewModel : ViewModel() {
    private val apiService = ApiService()

    val alimentos: MutableState<List<Alimentos>> = mutableStateOf(emptyList())


    suspend fun carregarAlimentos() {
        try {
            val alimentosRecebidos = apiService.getAlimentos()
            alimentos.value = alimentosRecebidos

        } catch (e: Exception) {
            // Handle errors here
            Log.e("AlimentosViewModel", "Error: ${e.message}")
        }
    }

}
