package com.melo.bottomnavjetpackcompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
 Scaffold(
  bottomBar = {BottomB(navController = navController)}
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

 BottomNavigation {
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
BottomNavigationItem(
 label = {
  Text(text = screen.title)
 },
 icon = {
    Icon(
        imageVector = screen.icon,
        contentDescription = "Navigation Icon",
      )
 },
 selected = currentDestination?.hierarchy?.any{
  it.route == screen.route
 } == true,
    unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

 onClick = {
  navController.navigate(screen.route)
 }


)
}
