package com.melo.bottomnavjetpackcompose.screens

import android.app.Dialog
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.navigation.NavHostController
import com.melo.bottomnavjetpackcompose.R
import com.melo.bottomnavjetpackcompose.api.ViewModels.AlimentosIngeridosViewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.CaloriasViewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.DeleteAlimentoIngerido
import com.melo.bottomnavjetpackcompose.api.ViewModels.UpdateCalorias
import com.melo.bottomnavjetpackcompose.api.dataClasses.Calorias
import com.melo.bottomnavjetpackcompose.screens.Dialogs.AlertDialogExample
import com.melo.bottomnavjetpackcompose.screens.Utils.Divisor
import java.lang.Integer.parseInt


@Composable
fun Home(  navController: NavHostController) {


Box(
    modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary)
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ,

){



    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        CardCaloriasDiarias()
        CardAlimentosIngeridos(AlimentosIngeridosViewModel())

    }

    }
    }





@Composable
fun CardCaloriasDiarias() {
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        modifier = Modifier
            .padding(top = 56.dp)
            .fillMaxWidth()
            .height(250.dp),
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
                text = "Calorias Diárias",
                modifier = paddingModifier,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge
            )
            Divider(color = Color(0xFF141414), modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
            Text(
                text = "Você deve consumir Abaixo do Limite diário",
                color = Color.Gray,
                modifier = paddingModifier,
                style = MaterialTheme.typography.labelSmall
            )

            val caloriasViewModel = CaloriasViewModel()
            CaloriasScreen(caloriasViewModel)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriasScreen(caloriasViewModel: CaloriasViewModel){

    // Observa os dados do ViewModel e mantém o estado usando remember


    val caloriasState by rememberUpdatedState(caloriasViewModel.caloriasAtual)
    val tmbState by rememberUpdatedState(caloriasViewModel.tmb)

    val calorias: Float = caloriasState.value.toFloat() ?: 0f
    val tmb: Float = tmbState.value.toFloat() ?: 1f // Definindo tmb como 1 se a conversão falhar para evitar a divisão por zero

    val porcentagem: Float = (calorias / tmb) * 100
    val porcentagemFormatada: String = String.format("%.2f", porcentagem)


    LaunchedEffect(true) {
        caloriasViewModel.carregarDados()
    }



    val openAlertDialogReset = remember { mutableStateOf(false) }
    val execReset = remember { mutableStateOf(false) }
    val UpdateCalorias = UpdateCalorias()


 if (execReset.value){
     LaunchedEffect(key1 = true){
         val Calorias = Calorias(202, 0,0,0,"")
         UpdateCalorias.updateCalorias(Calorias)
         caloriasViewModel.carregarDados()
     }
 }


    if (openAlertDialogReset.value) {
        AlertDialogExample(
            onDismissRequest = { openAlertDialogReset.value = false },
            onConfirmation = {
                execReset.value = true
                openAlertDialogReset.value = false

            },
            dialogTitle = "Resetar Calorias consumidas?",
            dialogText = "Você deseja resetar as calorias consumidas?",
            dismissOn = true,
            alertType = "Default"

        )
    }



    Text(
        text = "${caloriasState.value} / ${tmbState.value}",
        color = Color.White,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(top = 0.dp)
            .clickable {
                openAlertDialogReset.value = true
            }
    )
    Text(
        text = "${porcentagemFormatada}%",
        color = Color.White,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = 0.dp)
    )



    ProgressBar(progress = porcentagem.toInt())



}

@Composable
fun ProgressBar(progress: Int) {
    LinearProgressIndicator(
        progress = progress / 100f, // Calcula a porcentagem do progresso
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp),
    )
}



















@Composable
fun CardAlimentosIngeridos(alimentosViewModel: AlimentosIngeridosViewModel){
    val paddingModifier = Modifier.padding(10.dp)
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(bottom = 100.dp)
            .fillMaxWidth()
            .height(310.dp),
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
            Text(
                text = "Alimentos Ingeridos",
                modifier = paddingModifier,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge
            )




            Divisor()

            AlimentosIngeridosScreen(alimentosIngeridosViewModel = alimentosViewModel)



        }
    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlimentosIngeridosScreen(alimentosIngeridosViewModel: AlimentosIngeridosViewModel) {
    val alimentos by rememberUpdatedState(newValue = alimentosIngeridosViewModel.alimentosIngeridos.value)
    val openAlertDialog = remember { mutableStateOf(false) }
    val idItemSelected = remember { mutableIntStateOf(0) }
    val deleteAlimentos = DeleteAlimentoIngerido()
    val execDelete = remember { mutableStateOf(false) }




    LaunchedEffect(true) {
        alimentosIngeridosViewModel.carregarAlimentosIngeridos()
    }

    if (execDelete.value) {
        LaunchedEffect(true) {
            deleteAlimentos.deleteItem(parseInt(idItemSelected.value.toString()))
            alimentosIngeridosViewModel.carregarAlimentosIngeridos()
            execDelete.value = false

            //popup
        }
    }




    if (openAlertDialog.value) {
        AlertDialogExample(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                execDelete.value = true
                openAlertDialog.value = false

            },
            dialogTitle = "Retirar alimento ingerido #${idItemSelected.value}",
            dialogText = "Você deseja retirar o alimento ingerido?",
            dismissOn = false,
            alertType = "Default"

        )
    }










    // Verifica se os alimentos foram carregados
    if (alimentos.isEmpty()) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight() // Ocupa toda a altura disponível
                .padding(16.dp), // Adiciona algum espaçamento, se desejado
            verticalArrangement = Arrangement.Center, // Alinha os elementos verticalmente no centro
            horizontalAlignment = Alignment.CenterHorizontally // Alinha os elementos horizontalmente no centro
        ) {
            Image(
                painter = painterResource(id = R.drawable.plate),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(text = "Seu prato está vazio...", color = Color.Gray)
        }



    } else {

        // Renderiza a lista de alimentos
        LazyColumn {


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
                                text = "${alimento.alimento}",
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
                                idItemSelected.value = alimento.id


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





