package com.example.padsou.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NavBar(navController: NavHostController) {
    Card(elevation = 1.dp, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White,
                                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .padding(horizontal = 60.dp, vertical = 15.dp)
        ) {
            IconNavButton(navController = navController,
                    routeName = "Home", Icons.Rounded.Home)
            IconNavButton(navController = navController,
                    routeName = "AddPlan", Icons.Rounded.AddCircle)
            IconNavButton(navController = navController,
                    routeName = "Login", Icons.Rounded.AccountCircle)
        }
    }
}