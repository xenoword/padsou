package com.example.padsou.ui.pages.Home

import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {
    Divider(thickness =  40.dp, color = Color.Transparent)
    Text(text = "SWEET HOME ALABAMA")
}