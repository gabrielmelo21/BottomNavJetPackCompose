package com.melo.bottomnavjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.PesoViewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.UpdatePeso

import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import com.melo.bottomnavjetpackcompose.screens.Dialogs.AlertDialogExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePeso(){

    val execOperation = remember { mutableStateOf(false) }
    val pesoViewModel: PesoViewModel = viewModel()
    val updatePesoViewModel: UpdatePeso = viewModel();
    val pesoObjetivoState by rememberUpdatedState(pesoViewModel.pesoObjetivo)
    val pesoAtualState by rememberUpdatedState(pesoViewModel.pesoAtual)

    val pesoObjetivo: Float = pesoObjetivoState.value.toFloat()
    val pesoAtual: Float = pesoAtualState.value.toFloat()


    val openAlertDialog = remember { mutableStateOf(false) }

    var novoPeso by remember { mutableStateOf("") }


    LaunchedEffect(key1 = true ){
        pesoViewModel.carregarPeso()
    }

    // update peso view Model que possui aquele metodo

    if(execOperation.value){
        LaunchedEffect(key1 = true){

            val newPeso = Peso(null,  novoPeso.toDouble(), pesoObjetivo.toDouble(), "")

            updatePesoViewModel.updatePeso(newPeso)

            execOperation.value = false

            novoPeso = ""

            openAlertDialog.value = true
        }
    }




    if (openAlertDialog.value) {
        AlertDialogExample(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
            },
            dialogTitle = "Sucesso!",
            dialogText = "Sucesso ao atualizar peso.",
            dismissOn = false,
            alertType = "Success"

        )
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
        ){

        val paddingModifier = Modifier.padding(10.dp)
        Card(
            modifier = Modifier
                .padding(top = 56.dp)
                .fillMaxWidth()
                .height(325.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Adiciona preenchimento interno ao Column
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento vertical entre os elementos
            ) {
                Text(
                    text = "Atualizar Peso",
                    modifier = paddingModifier,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleLarge
                )
                Divider(
                    color = Color(0xFF141414), modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )






                TextField(
                    value =  novoPeso,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            novoPeso = it
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),

                    label = { Text("Peso Atual") },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                )










                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ElevatedButton(
                        onClick = {

                                execOperation.value = true

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0066ff),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp), // Borda arredondada com 16.dp de raio
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f) // Preenche o espaço disponível na linha
                    ) {
                        Text(text = "Atualizar")
                    }
                }




            }
        }
    }
}

