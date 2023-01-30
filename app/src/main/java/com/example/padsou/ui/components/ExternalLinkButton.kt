package com.example.padsou.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.padsou.ui.theme.Typography

@Composable
fun ExternalLinkButton(text: String, backgroundColor: Color,
                       onClick: () -> Unit){

    Button(colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(20F),
        onClick = onClick
    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp, fontStyle = FontStyle.Normal,
            fontFamily = Typography.h1.fontFamily, lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
    }
}