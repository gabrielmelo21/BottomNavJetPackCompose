package com.melo.bottomnavjetpackcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.melo.bottomnavjetpackcompose.api.ViewModels.AddAlimentosClass
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.screens.Dialogs.AlertDialogExample


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlimentos(){
    var nomeAlimento: String by remember { mutableStateOf("") }
    var calorias: String by remember { mutableStateOf("") }
    val execOperation = remember { mutableStateOf(false) }
    val openAlertDialog = remember { mutableStateOf(false) }
    val addAlimentosViewModel = AddAlimentosClass()




    if (execOperation.value){
         LaunchedEffect(key1 = true){
             val newAlimentos = Alimentos(null, nomeAlimento,calorias.toInt())
              addAlimentosViewModel.addAlimentos(newAlimentos)
             execOperation.value = false
             calorias = ""
             nomeAlimento = ""
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
            dialogText = "Sucesso ao Adicionar o Alimento",
            dismissOn = false,
            alertType = "Success"

        )
    }







        Box(

            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),

            ){


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {








                Card(
                    modifier = Modifier
                        .padding(top = 86.dp)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(370.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ){
                    // SnackbarFun()

                    Text(
                        text = "Adicionar novo Alimento",
                        modifier = Modifier.padding(20.dp),
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.titleSmall
                    )





                    Divider(color = Color(0xFF141414), modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp))


                    TextField(
                        value =  nomeAlimento,
                        onValueChange = {
                            nomeAlimento = it
                        },
                        label = { Text("Nome do Alimento") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    )


                    TextField(
                        value =  calorias,
                        onValueChange = {
                            if (it.isDigitsOnly()) {
                                calorias = it
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),

                        label = { Text("Calorias") },
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
                                if (!nomeAlimento.isEmpty() && !calorias.isEmpty()) {
                                    // Lógica quando o botão é clicado
                                    execOperation.value = true
                                } else {
                                    nomeAlimento = "Preencha o campo corretamente"
                                }
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
                            Text(text = "Adicionar Alimento")
                        }
                    }





                }




            }




        }



}