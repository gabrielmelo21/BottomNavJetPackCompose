package com.melo.bottomnavjetpackcompose.api.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAlimentosClass : ViewModel(){
    private val apiService = ApiService()
    val success: MutableState<Boolean> = mutableStateOf(false)
    suspend fun addAlimentos(alimentos: Alimentos){
         return withContext(Dispatchers.IO){
           try {
               apiService.addAlimentos(alimentos)
               success.value = true
           }catch (e: Exception){
                success.value = false
           }


         }
    }
}