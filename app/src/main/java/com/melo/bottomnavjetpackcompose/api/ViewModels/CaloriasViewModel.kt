package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melo.bottomnavjetpackcompose.api.ApiService
import kotlinx.coroutines.launch



class CaloriasViewModel : ViewModel() {
    private val apiService = ApiService()

    var caloriasAtual: MutableState<Int> = mutableStateOf(0)
    var tmb: MutableState<Int> = mutableStateOf(0)
    var deficitCalorico: MutableState<Int> = mutableStateOf(0)
    var dataDia: MutableState<String> = mutableStateOf("21/01")

    fun carregarDados() {
        viewModelScope.launch {
            try {
                val dadosRecebidos = apiService.getCalorias()
                for (cal in dadosRecebidos) {
                    caloriasAtual.value = cal.calorias_atual
                    tmb.value = cal.tmb
                    deficitCalorico.value = cal.deficit_calorico
                    dataDia.value = cal.data_dia

                    // Log dos valores (opcional)
                    Log.d(
                        "Calorias-ViewModel",
                        "${caloriasAtual.value} - ${tmb.value} - ${deficitCalorico.value} - ${dataDia.value}"
                    )
                }
            } catch (e: Exception) {
                // Trate os erros aqui
            }
        }
    }
}