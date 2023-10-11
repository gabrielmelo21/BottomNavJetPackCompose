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
        title = "",
        icon = Icons.Default.Home
    )

    object Peso: BottomBar(
        route = "peso",
        title = "",
        icon = Icons.Default.Face
    )

    object Deficit: BottomBar(
        route = "deficit",
        title = "",
        icon = Icons.Default.Person
    )

    object AddAlimentos: BottomBar(
        route = "AddAlimentos",
        title = "",
        icon = Icons.Default.Person
    )

    object AddAlimentosIngeridos: BottomBar(
        route = "AddAlimentosIngeridos",
        title = "",
        icon = Icons.Default.Person
    )


    object UpdatePeso: BottomBar(
        route = "UpdatePeso",
        title = "",
        icon = Icons.Default.Person
    )


}
