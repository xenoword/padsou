package com.example.padsou.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.ui.theme.LightGray

@Composable
fun IconNavButton(navController: NavHostController, routeName: String,
                  iconImgVector: ImageVector, color: Color) {
    IconButton(
            onClick = { navController.navigate(routeName) },
            modifier = Modifier
                    .size(50.dp)
                    .background(LightGray, RoundedCornerShape(5.dp))) {
        Icon(iconImgVector, null,
                Modifier.fillMaxSize(0.8f),
                tint = color)
    }
}

@Composable
fun IconNavButtonWithText(navController: NavHostController, routeName: String,
                          painter: Painter, bgAndTextColor: Color, iconColor: Color,
                          text: String = "") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
                onClick = { navController.navigate(routeName) },
                modifier = Modifier.padding(bottom = 5.dp)
                        .size(60.dp)
                        .background(bgAndTextColor, RoundedCornerShape(5.dp))) {
            Icon(painter, null,
                    Modifier.fillMaxSize(0.5f),
                    tint = iconColor)
        }

        Title(text = text, color = bgAndTextColor, size = 10.sp)
    }
}