package com.example.padsou.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Input (name: String, onValueChange: (String) -> Unit, placeholder: String)
{
    TextField(
        modifier = Modifier.fillMaxSize()
            .border(width = 1.dp, color = Color(0xFF0000), RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(1.dp),
        value = name,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
    )
}