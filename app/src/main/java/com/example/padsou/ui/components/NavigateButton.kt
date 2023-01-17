package com.example.padsou.ui.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

@Composable
fun NavigateButton(text: String, backgroundcolor: Color, navController: NavHostController, classDestination: String){

    Button(colors = ButtonDefaults.buttonColors(backgroundColor = backgroundcolor),
        onClick = {navController.navigate(classDestination)}
    ) {
        Text(text, fontWeight = FontWeight(400), fontSize = 16.sp, fontStyle = FontStyle.Normal,
            lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
    }
}