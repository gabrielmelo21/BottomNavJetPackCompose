package com.melo.bottomnavjetpackcompose.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBar(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Peso: BottomBar(
        route = "peso",
        title = "Seu Peso",
        icon = Icons.Default.Face
    )

    object Deficit: BottomBar(
        route = "deficit",
        title = "Deficit",
        icon = Icons.Default.Person
    )

}
