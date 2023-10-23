package com.melo.bottomnavjetpackcompose.screens.Dialogs

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.melo.bottomnavjetpackcompose.screens.Utils.Divisor
import java.lang.Integer.parseInt

@Composable
fun AddAlimentoIngeridoDialog(
      onDismissRequest: () -> Unit,
      onConfirmation: () -> Unit,
      AlimentoId: String,
      Alimento: String,
      Kcal: Int,
      Quant: Int

){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        val execOperation = remember { mutableStateOf(false) }
        var quantidade by remember { mutableStateOf("1") }
        val kcal by remember { mutableStateOf(Kcal.toString()) }



        val qInt = quantidade.toIntOrNull()
        val kInt = kcal.toIntOrNull()
        val r: Int
        if(qInt !== null && kInt !== null){
             r =  qInt * kInt
        }else{
             r = 0
        }

        val kcalFinal = remember {
            mutableStateOf(0)
        }
        kcalFinal.value = r







        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primary
        ){
            Box(
                contentAlignment = Alignment.Center
            ){
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(

                            text = "Adicionar Alimento Ingerido",
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                            ),
                        )

                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = colorResource(R.color.white),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { onDismissRequest() }
                        )
                    }
                    Divisor()
                    // Precisamso de alguns campos, como - Nome do alimento -

                    Text(
                        text = "Alimento: $Alimento",
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White
                        ),
                    )

                    Text(
                        text = "Calorias: ${kcalFinal.value} Kcal",
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White,

                        ),
                    )


                    TextField(
                        value = quantidade,
                        onValueChange = {
                            if (it.isDigitsOnly()) {
                                quantidade = it
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        label = { Text("Quantidade") },
                        textStyle = TextStyle(color = Color.White), // Definindo a cor do texto para branco
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    Divisor()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                   if (quantidade.isNotEmpty() || quantidade.toInt() !==0) {
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
                          Text(text = "Adicionar Alimento")
                        }

                    }
                    }







                }


            }
        }

    }
}