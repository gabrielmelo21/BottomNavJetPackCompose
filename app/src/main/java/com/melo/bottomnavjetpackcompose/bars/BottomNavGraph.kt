package com.melo.bottomnavjetpackcompose.bars

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melo.bottomnavjetpackcompose.screens.Deficit
import com.melo.bottomnavjetpackcompose.screens.Home
import com.melo.bottomnavjetpackcompose.screens.Peso

@Composable
fun BottomNavGraph(navController: NavHostController){
NavHost(
    navController = navController,
    startDestination = BottomBar.Home.route,
){
    composable(route = BottomBar.Home.route){
        Home()
    }
    composable(route = BottomBar.Peso.route){
        Peso()
    }
    composable(route = BottomBar.Deficit.route){
        Deficit()
    }

}
}
