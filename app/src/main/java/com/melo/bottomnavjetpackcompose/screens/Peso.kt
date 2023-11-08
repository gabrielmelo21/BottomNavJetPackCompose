package com.melo.bottomnavjetpackcompose.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.AlimentosViewModel
import com.melo.bottomnavjetpackcompose.api.ViewModels.PesoViewModel
import com.melo.bottomnavjetpackcompose.api.dataClasses.Peso
import com.melo.bottomnavjetpackcompose.screens.Utils.Format


@Composable
fun Peso(){
    val pesoViewModel: PesoViewModel = viewModel()

    val pesoAtualState by rememberUpdatedState(pesoViewModel.pesoAtual)
    val pesoObjetivoState by rememberUpdatedState(pesoViewModel.pesoObjetivo)
    val pesoInicialState by rememberUpdatedState(pesoViewModel.primeiroPeso)

    LaunchedEffect(key1 = true ){
         pesoViewModel.carregarPeso()
    }


    val pesoAtual: Float = pesoAtualState.value.toFloat()
    val pesoObjetivo: Float = pesoObjetivoState.value.toFloat()
    val pesoInicial: Float = pesoInicialState.value.toFloat()

    val format =  Format();
    val pesoPerdido =  format.format2((pesoAtual - pesoInicial).toString())


    val porcentagem: Float = (pesoObjetivo/pesoAtual) * 100
    val porcentagemFormatada: String = String.format("%.2f", porcentagem)




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ,


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
                    text = "Gerenciamento de Peso",
                    modifier = paddingModifier,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.titleLarge
                )
                Divider(
                    color = Color(0xFF141414), modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Text(
                    text = "Abaixo estão seu Peso Atual, seu Peso Objetivo e o Histórico de mudanças de peso.",
                    color = Color.Gray,
                    modifier = paddingModifier,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "${porcentagemFormatada}%",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 0.dp)
                )

                Progress(progress = porcentagem.toInt())

























                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Peso Atual",
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "${pesoAtualState.value} kg",
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Start
                        )

                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Peso Objetivo",
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${pesoObjetivoState.value} kg",
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }






                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()

                    ) {
                        Text(
                            text = "Progresso", modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth(),
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = "-${pesoPerdido} Kg",
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            color = Color.Green,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.End
                        )
                    }



                }





            }
        }

    }


}

@Composable
fun Progress(progress: Int) {
    LinearProgressIndicator(
        progress = progress / 100f, // Calcula a porcentagem do progresso
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp),
    )
}



