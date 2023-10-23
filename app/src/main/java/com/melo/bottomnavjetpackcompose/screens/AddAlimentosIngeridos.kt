package com.melo.bottomnavjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.AlimentosViewModel
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.screens.Dialogs.AddAlimentoIngeridoDialog
import com.melo.bottomnavjetpackcompose.screens.Utils.Divisor

@Composable
fun AddAlimentosIngeridos(){
    val alimentosViewModel: AlimentosViewModel = viewModel()
    val alimentos: List<Alimentos> = alimentosViewModel.alimentos.value
    val nomeAlimento = remember { mutableStateOf("") }
    val kcalAlimento = remember { mutableStateOf(0) }
    val idAlimento = remember { mutableStateOf("") }
    val openAlertDialog = remember { mutableStateOf(false) }


    if (openAlertDialog.value) {
        AddAlimentoIngeridoDialog(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = { openAlertDialog.value = false },
            AlimentoId = idAlimento.value,
            Alimento = nomeAlimento.value,
            Kcal = kcalAlimento.value,
            Quant = 1
        )
    }

    LaunchedEffect(true) {
        alimentosViewModel.carregarAlimentos()
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
                    .padding(top = 66.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(400.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ){

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Adiciona preenchimento interno ao Column
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento vertical entre os elementos
                ){
                    Text(text = "Oque você Ingeriu? ",  style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "Selecione o Alimento que você ingeriu, depois informe a quantidade.",  style = MaterialTheme.typography.titleSmall, color = Color.White,)
                    Divisor()



                    LazyColumn{
                        items(alimentos) { alimento ->


                            Row (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(3.dp)   .clickable{
                                        nomeAlimento.value =  alimento.alimento
                                        kcalAlimento.value  = alimento.calorias
                                        idAlimento.value = alimento.id.toString()

                                        openAlertDialog.value = true
                                    }
                            ) {
                                Column(   modifier = Modifier
                                    .fillMaxSize()
                                    .padding(3.dp)
                                ) {
                                    Text(text = "${alimento.alimento} - ${alimento.calorias} Kcal",
                                        modifier =
                                        Modifier.padding(16.dp)


                                    )
                                    Divisor()
                                }

                            }

                        }
                    }
                }


            }







        }




    }
}