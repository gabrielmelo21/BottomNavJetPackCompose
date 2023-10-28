package com.melo.bottomnavjetpackcompose

import android.annotation.SuppressLint
import android.graphics.drawable.Icon

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.LocalContentColor

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.TopAppBar

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.melo.bottomnavjetpackcompose.bars.BottomBar
import com.melo.bottomnavjetpackcompose.bars.BottomNavGraph



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
 val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route ?: ""
    var titleText = ""
    var floatActionRoute = ""

    var buttonRightTopAppBar = BottomBar.AddAlimentos.route
    var btnRightIcon: ImageVector = Icons.Default.Add

    when (rotaAtual) {
        "home" -> {
            floatActionRoute  = BottomBar.AddAlimentosIngeridos.route
            titleText = "Home"

            buttonRightTopAppBar = BottomBar.AddAlimentos.route
            btnRightIcon = Icons.Default.Add


        }
        "peso" -> {
            floatActionRoute  = BottomBar.UpdatePeso.route
            titleText = "Peso"


        }
        "deficit" -> {
            titleText = "Deficit"

        }
        "AddAlimentos" -> {
            titleText= "Adicionar alimentos mais ingeridos"

            buttonRightTopAppBar = BottomBar.DeleteAlimentos.route
            btnRightIcon = Icons.Default.Delete
        }

        "DeleteAlimentos" -> {
            titleText= "Deletar Alimentos "

            buttonRightTopAppBar = BottomBar.AddAlimentos.route
            btnRightIcon = Icons.Default.ArrowBack


        }


        "AddAlimentosIngeridos" -> {
            titleText= "Oque você Ingeriu?"

        }

        else -> {

        }
    }

    // Determinando qual ícone exibir com base na rota atual
    val icon: ImageVector = when (rotaAtual) {
        "home" -> Icons.Default.Add
        "peso" -> Icons.Default.Edit
        else -> Icons.Default.Add // Ícone padrão para outras rotas, se necessário
    }

 Scaffold(
     topBar = {

         TopAppBar(
             colors = TopAppBarDefaults.smallTopAppBarColors(
                 containerColor = MaterialTheme.colorScheme.primary,
                 navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                 actionIconContentColor = MaterialTheme.colorScheme.onSecondary
             ),


             title = {
                 Text(titleText, color = Color(0xFF0066ff))
             },



             actions = {
                 IconButton(
                     onClick = {
                         navController.navigate(  buttonRightTopAppBar )

                     }
                 ) {
                     Icon(imageVector = btnRightIcon, contentDescription = null)
                 }
             }


             )
     },
     bottomBar = { BottomB(navController = navController) },
     floatingActionButton = {
         if(rotaAtual == "home" || rotaAtual == "peso"){

             FloatingActionButton(onClick = {
                 navController.navigate(floatActionRoute)
             },
                 backgroundColor = Color(0xFF0066ff)) {Icon(icon, contentDescription = "Float Button", tint = Color.White) }

         }


     },


 ){



     BottomNavGraph(navController = navController)


 }
}


@Composable
fun BottomB(navController: NavHostController){
  val screens = listOf(
   BottomBar.Home,
   BottomBar.Peso,
   BottomBar.Deficit
  )
 val navBackStackEntry by navController.currentBackStackEntryAsState()
 val currentDestination = navBackStackEntry?.destination
    val mainColor =
 BottomNavigation(
     backgroundColor = MaterialTheme.colorScheme.primary
 ) {
  screens.forEach { screen ->
         AddItem(screen = screen, currentDestination = currentDestination , navController = navController )
  }
 }


}

@Composable
fun RowScope.AddItem(
  screen: BottomBar,
  currentDestination: NavDestination?,
  navController: NavHostController
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route ?: ""
    val tintIcon: Color
    if (rotaAtual == screen.route){
         tintIcon = Color(0xFF0066ff)
    }else{
         tintIcon = Color.White
    }
BottomNavigationItem(
/**label = {
    if (rotaAtual == screen.route){
        Text(text = screen.title, color = Color.White)
    }


        },*/
 icon = {
    Icon(
        imageVector = screen.icon,
        contentDescription = "Navigation Icon",
        tint = tintIcon,
      )
 },


 selected = currentDestination?.hierarchy?.any{ it.route == screen.route } == true,
 unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
selectedContentColor = Color.Blue,

 onClick = {
  navController.navigate(screen.route)
 }


)
}
