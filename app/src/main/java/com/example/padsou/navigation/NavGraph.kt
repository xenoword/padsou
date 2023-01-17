package com.example.padsou.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.padsou.ui.pages.Auth.Login
import com.example.padsou.ui.pages.Auth.Register
import com.example.padsou.ui.pages.Home.Home


@Composable
fun NavGraph (){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Login")
    {
        composable(route = "Login"){Login(navController)}
        composable(route = "Home"){Home(navController)}
        composable(route = "Register"){Register(navController)}
//        composable(route = "Profil"){Profil(navController)}
    }
}