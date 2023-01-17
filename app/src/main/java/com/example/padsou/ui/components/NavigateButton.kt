package com.example.padsou.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*

@Composable
fun NavigateButton(text: String, backgroundcolor: Color, navController: NavHostController, classDestination: String){

    Button(colors = ButtonDefaults.buttonColors(backgroundColor = backgroundcolor),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(20F),
        onClick = {navController.navigate(classDestination)}
    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp, fontStyle = FontStyle.Normal,
            lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
    }
}