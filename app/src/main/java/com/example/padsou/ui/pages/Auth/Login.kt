package com.example.padsou.ui.pages.Auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.padsou.navigation.NavGraph
import com.example.padsou.ui.components.Input
import com.example.padsou.ui.components.NavigateButton
import com.example.padsou.ui.components.Title
import com.example.padsou.ui.theme.MediumBlue

@Composable
fun Login(navController: NavHostController) {
    var mailValue by remember { mutableStateOf("") }
    var pwdValue by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(thickness = 80.dp, color = Color.Transparent)
        Title(text = "Te revoilà ! 🔥", color = Color.Black)
        Text(text = "Reviens vite pour profiter\n des bons plans", textAlign = TextAlign.Center)

        Divider(thickness = 40.dp, color = Color.Transparent)
        Input(name = mailValue, onValueChange = { mailValue = it },
                placeholder = "Ton adresse e-mail")

        Divider(thickness = 20.dp, color = Color.Transparent)
        Input(name = pwdValue, onValueChange = { pwdValue = it },
                placeholder = "Ton mot de passe", kbType = KeyboardType.Password)

        Divider(thickness = 20.dp, color = Color.Transparent)

        Text("Mot de passe oublié ?", textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(0.85f))

        Divider(thickness = 40.dp, color = Color.Transparent)
        Row(Modifier.fillMaxWidth(0.85F).align(Alignment.CenterHorizontally)) {
            NavigateButton(text = "SE CONNECTER", backgroundcolor = MediumBlue,
                navController = navController, classDestination = "Home")
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.Bottom) {
            ClickableText(
                    modifier = Modifier.padding(bottom = 35.dp),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Pas encore inscrit ?")
                        }
                        withStyle(style = SpanStyle(color = MediumBlue)) {
                            append(" Allez viens !")
                        }
                    }, onClick = { navController.navigate("Register") }
            )
        }
    }
}