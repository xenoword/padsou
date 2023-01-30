package com.example.padsou.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.padsou.ui.theme.Typography

@Composable
fun Title(text: String, color: Color, size: TextUnit = 30.sp){
    Text(text.uppercase(), style = Typography.h1, fontSize = size, color = color)
}
