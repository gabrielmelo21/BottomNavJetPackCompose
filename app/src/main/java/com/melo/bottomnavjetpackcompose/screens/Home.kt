package com.melo.bottomnavjetpackcompose.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.melo.bottomnavjetpackcompose.api.API_CALLS
import com.melo.bottomnavjetpackcompose.api.Alimentos
import com.melo.bottomnavjetpackcompose.api.AlimentosViewModel
import com.melo.bottomnavjetpackcompose.api.ApiService
import com.melo.bottomnavjetpackcompose.bars.BottomBar
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
       val  alimentosViewModel =  AlimentosViewModel()
        CardAlimentosIngeridos(alimentosViewModel)



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
            .height(210.dp),
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
            Divider(color = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
            Text(
                text = "Você deve consumir Abaixo do Limite diário",
                color = Color.Gray,
                modifier = paddingModifier,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "500 / 1800",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 0.dp)
            )

            Text(
                text = "50%",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 0.dp)
            )

            ProgressBar(progress = 50)
        }
    }
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
fun CardAlimentosIngeridos(alimentosViewModel: AlimentosViewModel){
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
            Divider(color = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))
           
         AlimentosScreen(alimentosViewModel = alimentosViewModel)
        // LazyColumnExample()


        }
    }
}






@Composable
fun AlimentosScreen(alimentosViewModel: AlimentosViewModel) {
    val alimentos = alimentosViewModel.alimentos.value

    LaunchedEffect(true) {
        alimentosViewModel.carregarAlimentos()
    }

    // Verifica se os alimentos foram carregados
    if (alimentos == null || alimentos.isEmpty()) {
        // Exibe um indicador de carregamento ou uma mensagem de erro, conforme necessário
        // Exemplo de indicador de carregamento:
        // CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(16.dp))
        // Ou mensagem de erro:
        // Text(text = "Erro ao carregar alimentos", modifier = Modifier.padding(16.dp))
    } else {
        // Renderiza a lista de alimentos
        LazyColumn {
            items(alimentos) { alimento ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Text(
                        text = "${alimento.alimento}",
                        modifier = Modifier.weight(1f).padding(end = 8.dp),

                    )
                    Text(
                        text = "${alimento.calorias} kcal",
                        textAlign = TextAlign.Start,
                        style = TextStyle(textAlign = TextAlign.Start) // Alinha o texto à esquerda
                    )
                }
               
               // Divider(color = Color.Gray, thickness = 0.5.dp, modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
}



