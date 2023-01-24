package com.example.padsou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.padsou.models.Plan
import com.example.padsou.ui.pages.AddPlan.AddPlan
import com.example.padsou.ui.pages.Onboarding.Onboarding
import com.example.padsou.ui.pages.Auth.Login
import com.example.padsou.ui.pages.Auth.Register
import com.example.padsou.ui.pages.Home.Home
import com.example.padsou.ui.pages.PlanDetail.PlanDetail
import com.example.padsou.ui.pages.Search.Search


@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = "Home")
    {
        composable(route = "Onboarding") { Onboarding(navController) }
        composable(route = "Login") { Login(navController) }
        composable(route = "Home") { Home(navController, mutableListOf<Plan>()) }
        composable(route = "Register") { Register(navController) }
        composable(route = "AddPlan") { AddPlan(navController) }
        composable("Search/{searchTerm}",
                arguments = listOf(navArgument("searchTerm") { defaultValue = "" })
        ) { backStackEntry ->
            Search(navController, backStackEntry.arguments?.getString("searchTerm"))
        }
        composable(route = "Detail/{planId}"){
                navBackStackEntry ->
            val planId = navBackStackEntry.arguments?.getString("planId")
            planId?.let {
                PlanDetail(it)
            }
        }
//        composable(route = "Profil"){Profil(navController)}
    }
}