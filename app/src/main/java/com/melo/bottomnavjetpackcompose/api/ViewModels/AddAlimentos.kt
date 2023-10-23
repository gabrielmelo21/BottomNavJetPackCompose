package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAlimentosClass : ViewModel(){
    private val apiService = ApiService()

    suspend fun addAlimentos(alimentos: Alimentos){
         return withContext(Dispatchers.IO){
           try {
               apiService.addAlimentos(alimentos)
           }catch (e: Exception){
              Log.e("addAlimentos", "${e.message}")
           }


         }
    }
}