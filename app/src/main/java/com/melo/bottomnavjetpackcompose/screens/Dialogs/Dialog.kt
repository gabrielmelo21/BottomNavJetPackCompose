package com.melo.bottomnavjetpackcompose.screens.Dialogs


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.android.gms.wallet.button.ButtonConstants


class DialogColors(val bg: Color, val txt: Color, val btn: Color)


@ExperimentalMaterial3Api
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    dismissOn: Boolean?,
    alertType: String
) {


    val dialogColors =   when(alertType){
       "Default" -> {
         DialogColors(txt = Color.White,bg = Color(0xFF323333), btn = Color(0xFFFFFFFF))
       }
       "Success" -> {
         DialogColors(txt = Color.White,bg = Color(0xFF4CAF50), btn = Color(0xFF388E3C))
       }
        else -> {
         DialogColors(txt = Color.White,bg = Color(0xFF4CAF50), btn = Color(0xFF388E3C))
            }
        }

/**
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = dialogColors.btn, // Cor de fundo do botão
        contentColor = Color.White // Cor do texto do botão
    )
**/

Dialog(onDismissRequest = { onDismissRequest() }) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = dialogColors.bg
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = dialogTitle,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = dialogColors.txt
                    )

                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = colorResource(android.R.color.white),
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clickable { onDismissRequest() }
                    )
                }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = dialogText, color = dialogColors.txt )

                    Spacer(modifier = Modifier.height(20.dp))

                    if(dismissOn == false){ //apenas 1 botão
                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
                         
                            Button(onClick = {
                                onConfirmation()
                            },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)

                                ) {

                                Text(text = "Confirmar", color = dialogColors.txt)
                            }
                        }

                    }

                if (dismissOn == true) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                onDismissRequest()
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(5.dp),

                        ) {
                            Text(text = "Fechar")
                        }

                        Button(
                            onClick = {
                                onConfirmation()
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f)
                                .padding(5.dp),

                        ) {
                            Text(text = "Confirmar")
                        }
                    }
                }






            }

        }
    }

}






}

