package com.example.padsou.ui.pages.Search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Search (navHostController: NavHostController, searchTerm: String?){
    Text("Terme recherché : " + (searchTerm ?: "Default"))
}