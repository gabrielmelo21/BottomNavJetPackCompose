package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.api.dataClasses.AlimentosIngeridos

class AlimentosIngeridosViewModel: ViewModel() {
    private val apiService = ApiService()
    val alimentosIngeridos: MutableState<List<AlimentosIngeridos>> = mutableStateOf(emptyList())


    suspend fun carregarAlimentosIngeridos() {
        try {
            val alimentosIngeridosRecebidos = apiService.getAlimentosIngeridos()
            // colondos os dados recebidos do retrofit em nossa variavel mutableState de array
            alimentosIngeridos.value = alimentosIngeridosRecebidos
        } catch (e: Exception) {
            // Handle errors here
            Log.e("AlimentosViewModel", "Error: ${e.message}")
        }
    }

}