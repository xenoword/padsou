package com.example.padsou.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.padsou.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.inter_medium),
            //Font(R.font.integral_cf_bold)
        ),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    h1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.integral_cf_bold),
            //Font(R.font.integral_cf_bold)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)