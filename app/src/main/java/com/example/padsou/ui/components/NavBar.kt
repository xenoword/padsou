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
import com.example.padsou.ui.theme.LightGrayIcon
import com.example.padsou.ui.theme.MediumBlue

@Composable
fun NavBar(navController: NavHostController) {

    fun getIconColor(route :String) : Color
    {
        if (navController.currentBackStackEntry?.destination?.route === route)
        {
            return MediumBlue
        }
        return LightGrayIcon
    }

    Card(elevation = 16.dp, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White,
                                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .padding(horizontal = 60.dp, vertical = 15.dp)
        ) {
            IconNavButton(navController = navController,
                    routeName = "Home", Icons.Rounded.Home, color = getIconColor("Home"))
            IconNavButton(navController = navController,
                    routeName = "AddPlan", Icons.Rounded.AddCircle, color = getIconColor("AddPlan"))
            IconNavButton(navController = navController,
                    routeName = "Profil", Icons.Rounded.AccountCircle, color = getIconColor("Profil"))
        }
    }
}