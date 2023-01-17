package com.example.padsou.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.padsou.ui.pages.Onboarding.Onboarding


@Composable
fun NavGraph (){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Onboarding")
    {
        composable(route = "Onboarding"){ Onboarding(navController) }
//        composable(route = "Profil"){Profil(navController)}
    }
}