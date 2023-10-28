package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.api.dataClasses.AlimentosIngeridos
import com.melo.bottomnavjetpackcompose.bars.BottomBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAlimentosIngeridosViewModel : ViewModel() {
    private val apiService = ApiService()

    suspend fun AddAlimentosIngeridos(alimentosIngeridos: AlimentosIngeridos){
        return withContext(Dispatchers.IO){
             try {
                 apiService.addAlimentosIngeridos(alimentosIngeridos)
             }catch (e: Exception){
                 Log.e("addAlimentos", "${e.message}")
             }
        }

    }
}