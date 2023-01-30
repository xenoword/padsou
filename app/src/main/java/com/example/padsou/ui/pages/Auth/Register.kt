package com.example.padsou.ui.pages.Auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.padsou.models.User
import com.example.padsou.ui.components.Input
import com.example.padsou.ui.components.Title
import com.example.padsou.ui.components.ValidationButton
import com.example.padsou.ui.theme.MediumBlue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth

@Composable
fun Register(navController: NavHostController) {
    val inputSpacing = 20.dp

    var context = LocalContext.current
    val db = Firebase.firestore

    var mailValue by remember { mutableStateOf("") }
    var pwdValue by remember { mutableStateOf("") }
    var pwdConfirmValue by remember { mutableStateOf("") }

    Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
                modifier = Modifier
                        .fillMaxWidth(0.85f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(thickness = 80.dp, color = Color.Transparent)
            Title(text = "Bienvenue 😎", color = Color.Black)
            Text(text = "Inscris-toi pour avoir les\n" +
                    "meilleurs plans étudiants !", textAlign = TextAlign.Center)

            Divider(thickness = 40.dp, color = Color.Transparent)
            Input(name = mailValue, onValueChange = { mailValue = it },
                    placeholder = "Ton adresse e-mail")

            Divider(thickness = inputSpacing, color = Color.Transparent)
            Input(name = pwdValue, onValueChange = { pwdValue = it },
                    placeholder = "Ton mot de passe", kbType = KeyboardType.Password)

            Divider(thickness = inputSpacing, color = Color.Transparent)
            Input(name = pwdConfirmValue, onValueChange = { pwdConfirmValue = it },
                    placeholder = "Confirmer ton mot de passe", kbType = KeyboardType.Password)

            Divider(thickness = 60.dp, color = Color.Transparent)
            Text("En t'inscrivant, tu acceptes les Conditions générales" +
                    " d'utilisation de Padsou", textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth())

            Divider(thickness = 10.dp, color = Color.Transparent)
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)) {
                ValidationButton(
                        text = "S'INSCRIRE", backgroundcolor = MediumBlue,
                        enabled = (!mailValue.isNullOrBlank() && !pwdValue.isNullOrBlank() && !pwdConfirmValue.isNullOrBlank()),
                        onClick = {
                            auth = Firebase.auth

                            if (pwdValue != pwdConfirmValue){
                                pwdValue = ""
                                pwdConfirmValue = ""
                                Toast.makeText(context, "Your password must match the confirmation password.",
                                    Toast.LENGTH_LONG).show()
                            }
                            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mailValue).matches()){
                                mailValue = ""
                                Toast.makeText(context, "Incorrect email.",
                                    Toast.LENGTH_LONG).show()
                            }
                            else{

                                auth.createUserWithEmailAndPassword(mailValue, pwdValue)
                                    .addOnCompleteListener(ComponentActivity()) { task ->
                                        if (task.isSuccessful) {

                                            var newUser = User("", mailValue.substring(0,mailValue.indexOf("@")), "https://c0.lestechnophiles.com/www.numerama.com/wp-content/uploads/2017/03/nier2-680x370.jpg?webp=1&key=18e4befb",
                                            mailValue)

                                            db.collection("users")
                                                .document(auth.currentUser!!.uid)
                                                .set(newUser.toFirebaseHashMap())
                                                .addOnSuccessListener { Log.d("TAG", "Utilisateur créé avec succès!")}
                                                .addOnFailureListener { e -> Log.w("TAG", "Erreur lors de la création de l'utilisateur !", e) }

                                            val user = auth.currentUser
                                            if (user != null) {
                                                updateUI(user, navController, context)
                                            }
                                        } else {
                                            Toast.makeText(context, "Authentication failed. Maybe your password is not long enough",
                                                Toast.LENGTH_LONG).show()
                                        }
                                    }
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(verticalAlignment = Alignment.Bottom) {
                ClickableText(
                        modifier = Modifier.padding(bottom = 35.dp),
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("Déjà un compte ?")
                            }
                            withStyle(style = SpanStyle(color = MediumBlue)) {
                                append(" Connecte-toi !")
                            }
                        }, onClick = { navController.navigate("Login") }
                )
            }
        }
    }
}

fun updateUI(account: FirebaseUser, navController: NavHostController, context: Context) {
    Toast.makeText(context, "You signed in successfully", Toast.LENGTH_LONG).show()
        navController.navigate("Home")
}