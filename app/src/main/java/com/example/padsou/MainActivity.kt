package com.example.padsou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.padsou.navigation.NavGraph
import com.example.padsou.ui.components.Input
import com.example.padsou.ui.pages.Onboarding.Onboarding
import com.example.padsou.ui.theme.PadsouTheme
import com.example.padsou.ui.theme.Pink
import com.example.padsou.ui.theme.PrimaryOrange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PadsouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var text by remember { mutableStateOf("") }

    Input(name = text, onValueChange = { newText ->
        text = newText }, placeholder = "zebi rempli moi")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PadsouTheme {
        Greeting()
    }
}