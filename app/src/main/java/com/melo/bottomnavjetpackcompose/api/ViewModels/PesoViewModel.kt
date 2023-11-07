package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import kotlinx.coroutines.launch
import kotlin.math.log

class PesoViewModel : ViewModel() {
    private val apiService = ApiService()


    val pesoHistorico: MutableState<List<Peso>> = mutableStateOf(emptyList())

    val pesoAtual: MutableState<Double> = mutableStateOf(0.00)
    val pesoObjetivo: MutableState<Double> = mutableStateOf(0.00)
    val dataDia: MutableState<String> = mutableStateOf("21/01")




    suspend fun carregarPeso() {
        viewModelScope.launch {
            try {
                pesoHistorico.value  = apiService.getPeso()
                val ultimoPeso = pesoHistorico.value.lastOrNull()

                if (ultimoPeso != null) {

                        dataDia.value = ultimoPeso.data_dia
                        pesoAtual.value = ultimoPeso.peso
                        pesoObjetivo.value = ultimoPeso.peso_objetivo


                    Log.i( "now2","Último Peso - ID: ${ultimoPeso.id}, Peso: ${ultimoPeso.peso}, Peso Objetivo: ${ultimoPeso.peso_objetivo}, Data: ${ultimoPeso.data_dia}")
                } else {
                    Log.i("now2" , "A lista de pesos está vazia.")
                }

                Log.i("now", "Listado ${pesoHistorico.value}")
            } catch (e: Exception) {
                // Handle errors here
                Log.e("PesoViewModel", "Error: ${e.message}")
            }
        }

    }
}