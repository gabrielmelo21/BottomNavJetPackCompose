package com.melo.bottomnavjetpackcompose.api.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeletarAlimento : ViewModel() {

    private val apiService = ApiService()


    suspend fun deleteItem(itemId: Int) {
        return withContext(Dispatchers.IO){
            try {
                apiService.deletarAlimento(itemId)
                // Lógica para lidar com a exclusão bem-sucedida, se necessário
            } catch (e: Exception) {
                // Lógica para lidar com erros durante a exclusão
                Log.e("DeleteAlimento", "Erro ao excluir item: ${e.message}")
            }
        }


    }

}