package com.example.padsou.ui.components

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.padsou.ui.theme.Typography

@Composable
fun ExternalLinkButton(text: String, backgroundcolor: Color, link: String){
    val ctx = LocalContext.current

    Button(colors = ButtonDefaults.buttonColors(backgroundColor = backgroundcolor),
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(20F),
        onClick = {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(link)
            )
            ctx.startActivity(urlIntent)
        }
    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp, fontStyle = FontStyle.Normal,
            fontFamily = Typography.h1.fontFamily, lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
    }
}