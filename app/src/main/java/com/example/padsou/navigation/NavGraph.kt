package com.example.padsou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.padsou.ui.pages.addPlan.AddPlan
import com.example.padsou.ui.pages.onboarding.Onboarding
import com.example.padsou.ui.pages.auth.Login
import com.example.padsou.ui.pages.auth.Register
import com.example.padsou.ui.pages.home.Home
import com.example.padsou.ui.pages.planDetail.PlanDetail
import com.example.padsou.ui.pages.profil.Profil


@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = "Onboarding")
    {
        composable(route = "Onboarding") { Onboarding(navController) }
        composable(route = "Login") { Login(navController) }
        composable(route = "Home") { Home(navController) }
        composable(route = "Register") { Register(navController) }
        composable(route = "AddPlan") { AddPlan(navController) }
        composable(route = "Detail/{planId}"){
                navBackStackEntry ->
            val planId = navBackStackEntry.arguments?.getString("planId")
            planId?.let {
                PlanDetail(it)
            }
        }
        composable(route = "Profil"){Profil(navController)}
    }
}