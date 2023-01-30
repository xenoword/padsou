package com.example.padsou.ui.pages.Profil

import android.util.Log
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.padsou.models.Plan
import com.example.padsou.models.User
import com.example.padsou.ui.components.*
import com.example.padsou.ui.theme.LightGray
import com.example.padsou.ui.theme.MediumBlue
import com.example.padsou.ui.theme.Typography
import com.example.padsou.ui.theme.White
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth

@Composable
fun Profil(navController: NavHostController) {

    var pseudoValue by remember { mutableStateOf("") }
    var mailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var pictureLinkValue by remember { mutableStateOf("") }

    val db = Firebase.firestore
    auth = Firebase.auth

    auth.currentUser?.let {
        LaunchedEffect(Unit) {
            db.collection("users")
                .document(it.uid)
                .get()
                .addOnSuccessListener { document ->

                        val user: User = document.toObject()!!
                        user.id = document.id

                        pseudoValue = user.pseudo
                        mailValue = user.mail
                        pictureLinkValue = user.profilePicture
                    }
                }
        }

    Surface(modifier = Modifier.fillMaxSize(),
        color = MediumBlue) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Divider(thickness = 40.dp, color = Color.Transparent)
            Column(modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Row() {
                    Title("Mon profil", Color.White)

                    IconButton(
                        onClick = { /* TODO add user picture */ },
                        modifier = Modifier
                            .padding(start= 100.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(50))
                            .background(White, RoundedCornerShape(10.dp))
                    ) {
                        if (pictureLinkValue === "") {
                            Icon(
                                Icons.Rounded.Add, null,
                                Modifier.fillMaxSize(0.9f),
                                tint = MediumBlue
                            )
                        } else {
                            AsyncImage(
                                model = pictureLinkValue,
                                contentDescription = "user image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
                Divider(thickness = 0.dp, color = Color.Transparent)
                Text("Modifie tes infos affichées aux autres sur l’app",
                    color = Color.White,
                    fontSize = 20.sp)
            }

            Divider(thickness = 40.dp, color = Color.Transparent)
            Surface(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = LightGray) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 0.dp, vertical = 10.dp)
                    //.verticalScroll(rememberScrollState())
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 70.dp)) {

                        Divider(thickness = 40.dp, color = Color.Transparent)
                        Title(text = "mon pseudo", color = Color.Black, size = 18.sp)
                        Divider(thickness = 5.dp, color = Color.Transparent)
                        Input(name = pseudoValue, onValueChange = { pseudoValue = it }, placeholder = "")

                        Divider(thickness = 30.dp, color = Color.Transparent)
                        Title(text = "Adresse E-mail", color = Color.Black, size = 18.sp)
                        Divider(thickness = 5.dp, color = Color.Transparent)
                        Input(name = mailValue, onValueChange = { mailValue = it }, placeholder = "")

                        Divider(thickness = 30.dp, color = Color.Transparent)
                        Title(text = "Mot de passe", color = Color.Black, size = 18.sp)
                        Divider(thickness = 5.dp, color = Color.Transparent)
                        Input(name = passwordValue, onValueChange = { passwordValue = it }, placeholder = "", kbType = KeyboardType.Password)

                        Divider(thickness = 50.dp, color = Color.Transparent)
                        Button(colors = ButtonDefaults.buttonColors(backgroundColor = MediumBlue),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, bottom = 10.dp)
                                .height(50.dp),
                            shape = RoundedCornerShape(20F),
                            onClick = {
                                //TODO maj user
                            }
                        ) {
                            Text("ENREGISTRER", fontWeight = FontWeight.Bold, fontSize = 16.sp, fontStyle = FontStyle.Normal,
                                fontFamily = Typography.h1.fontFamily, lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
                        }

                        Divider(thickness = 60.dp, color = Color.Transparent)
                    }
                }

                Row(modifier = Modifier
                    .fillMaxSize(),
                    verticalAlignment = Alignment.Bottom) {
                    NavBar(navController = navController)
                }
            }
        }
    }
}
