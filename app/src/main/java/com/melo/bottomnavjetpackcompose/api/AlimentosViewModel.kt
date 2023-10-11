package com.melo.bottomnavjetpackcompose.api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

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
