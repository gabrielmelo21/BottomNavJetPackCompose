package com.melo.bottomnavjetpackcompose.bars

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.melo.bottomnavjetpackcompose.screens.AddAlimentos
import com.melo.bottomnavjetpackcompose.screens.AddAlimentosIngeridos
import com.melo.bottomnavjetpackcompose.screens.Deficit
import com.melo.bottomnavjetpackcompose.screens.DeleteAlimentos
import com.melo.bottomnavjetpackcompose.screens.Home
import com.melo.bottomnavjetpackcompose.screens.Peso
import com.melo.bottomnavjetpackcompose.screens.UpdatePeso

@Composable
fun BottomNavGraph(navController: NavHostController){
NavHost(
    navController = navController,
    startDestination = BottomBar.Home.route,
){
    composable(route = BottomBar.Home.route){
        Home(navController)
    }
    composable(route = BottomBar.Peso.route){
        Peso()
    }
    composable(route = BottomBar.Deficit.route){
        Deficit()
    }
    composable(route = BottomBar.AddAlimentos.route){
        AddAlimentos()
    }

    composable(route = BottomBar.AddAlimentosIngeridos.route){
        AddAlimentosIngeridos()
    }
    composable(route = BottomBar.DeleteAlimentos.route){
        DeleteAlimentos()
    }

    composable(route = BottomBar.UpdatePeso.route){
         UpdatePeso()
    }


}
}
