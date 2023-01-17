package com.example.padsou.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.padsou.ui.theme.LightGray
import com.example.padsou.ui.theme.MediumBlue

@Composable
fun NavBar(navController: NavHostController) {
    Card(elevation = 1.dp
            ,shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White,
                                RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .padding(horizontal = 60.dp, vertical = 15.dp)
        ) {
            NavButton(navController = navController,
                    routeName = "Home", Icons.Rounded.Home)
            NavButton(navController = navController,
                    routeName = "AddPlan", Icons.Rounded.AddCircle)
            NavButton(navController = navController,
                    routeName = "Login", Icons.Rounded.AccountCircle)
        }
    }
}

@Composable
fun NavButton(navController: NavHostController, routeName: String, iconImgVector: ImageVector) {
    IconButton(

            onClick = { navController.navigate(routeName) },
            modifier = Modifier.size(50.dp)
                    .background(LightGray, RoundedCornerShape(5.dp))) {
        Icon(iconImgVector, null,
                Modifier.fillMaxSize(0.8f),
        tint = MediumBlue)
    }
}