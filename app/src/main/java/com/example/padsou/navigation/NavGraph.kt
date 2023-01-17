package com.example.padsou.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavGraph (){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home")
    {
//        composable(route = "Home"){Home(navController)}
//        composable(route = "Profil"){Profil(navController)}
    }
}