package com.example.padsou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.padsou.ui.pages.Onboarding.Onboarding
import com.example.padsou.ui.pages.Auth.Login
import com.example.padsou.ui.pages.Auth.Register
import com.example.padsou.ui.pages.Home.Home
import com.example.padsou.ui.pages.PlanDetail.PlanDetail


@Composable
fun NavGraph (){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Onboarding")
    {
        composable(route = "Onboarding"){ Onboarding(navController) }
        composable(route = "Login"){Login(navController)}
        composable(route = "Home"){Home(navController)}
        composable(route = "Register"){Register(navController)}
        composable(route = "Detail/{planId}"){
                navBackStackEntry ->
            val planId = navBackStackEntry.arguments?.getString("planId");
            planId?.let {
                PlanDetail(it)
            }
        }
//        composable(route = "Profil"){Profil(navController)}
    }
}