package com.melo.bottomnavjetpackcompose.screens.Utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Divisor(){
  Divider(
        color = Color(0xFF141414), modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )

}