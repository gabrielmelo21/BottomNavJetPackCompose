package com.melo.bottomnavjetpackcompose.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.melo.bottomnavjetpackcompose.R
import com.melo.bottomnavjetpackcompose.api.ViewModels.AlimentosViewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.DeletarAlimento
import com.melo.bottomnavjetpackcompose.api.dataClasses.Alimentos
import com.melo.bottomnavjetpackcompose.screens.Dialogs.AlertDialogExample
import com.melo.bottomnavjetpackcompose.screens.Utils.Divisor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAlimentos(){
    val alimentosViewModel: AlimentosViewModel = viewModel()
    val alimentos: List<Alimentos> = alimentosViewModel.alimentos.value
    val execDelete = remember { mutableStateOf(false) }
    val DeletarAlimento = DeletarAlimento()

    val openAlertDialog = remember { mutableStateOf(false) }
    val idItemSelected = remember { mutableIntStateOf(0) }

    LaunchedEffect(true) {
        alimentosViewModel.carregarAlimentos()
    }



    if (execDelete.value) {
        LaunchedEffect(true) {
            DeletarAlimento.deleteItem(idItemSelected.value)
            alimentosViewModel.carregarAlimentos()
            execDelete.value = false

        }
    }

    if (openAlertDialog.value) {
        AlertDialogExample(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                execDelete.value = true
                openAlertDialog.value = false

            },
            dialogTitle = "Retirar alimento #${idItemSelected.value}",
            dialogText = "Você deseja retirar o alimento?",
            dismissOn = false,
            alertType = "Default"

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
                    Text(text = "Deletar Alimento ",  style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "Selecione o Alimento que você deseja deletar..",  style = MaterialTheme.typography.titleSmall, color = Color.White,)
                    Divisor()



                    LazyColumn{

                        items(alimentos) { alimento ->
                            Card(
                                shape = RoundedCornerShape(20.dp), // Define cantos arredondados com um raio de 8dp
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF141414),
                                ),


                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)

                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp) // Espaçamento interno dentro do Card
                                ) {
                                    Column {
                                        Text(
                                            text = alimento.alimento,
                                            color = Color.White, // Cor do texto definida pelo tema
                                            fontWeight = FontWeight.Bold, // Texto em negrito
                                            fontSize = 18.sp, // Tamanho da fonte
                                        )
                                        Text(
                                            text = "${alimento.calorias} kcal",
                                            color = MaterialTheme.colorScheme.tertiary, // Cor do texto definida pelo tema
                                            fontSize = 14.sp, // Tamanho da fonte
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            // Lógica para remover o item associado ao ícone

                                        openAlertDialog.value = true
                                        idItemSelected.value = alimento.id?.toInt() ?: 0


                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete, // Ícone de remoção padrão do Material Design
                                            contentDescription = null, // Descrição de conteúdo nula para fins de acessibilidade
                                            tint = Color.Red // Cor do ícone definida pelo tema
                                        )
                                    }
                                }
                            }


                        }

                    }
                }


            }







        }




    }
}