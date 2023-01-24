package com.example.padsou.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.padsou.models.Plan
import com.example.padsou.models.User
import com.example.padsou.ui.theme.Teal200
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.logging.Logger.global

@Composable
fun PlanPreview(plan: Plan, navController: NavHostController, height: Dp, width: Dp){
    val db = Firebase.firestore
    var author by remember { mutableStateOf(User()) }

    db.collection("users")
        .document(plan.author)
        .get()
        .addOnSuccessListener { document ->
            document.toObject<User>()?.let { it ->
                author = it
                author.id = plan.author
            }
        }

    Column(Modifier.height(height).width(width)) {
        Box(Modifier.fillMaxHeight(0.66F).fillMaxWidth()){
            Row(Modifier.fillMaxSize()) {
                AsyncImage(
                    model = plan.image,
                    contentDescription = "Plan image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8F)
                        .clip(RoundedCornerShape(10))
                )
            }
            Row(Modifier.fillMaxSize(), Arrangement.Center, Alignment.Bottom) {
                AsyncImage(
                    model = author.profilePicture,
                    contentDescription = "Author profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize(0.3F)
                        .border(
                            BorderStroke(2.dp, Color.White),
                            CircleShape
                        )
                        .clip(CircleShape)
                )
            }
        }

        Row() {
            Text(text = plan.title, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Row() {
            Text(text = plan.subTitle, fontSize = 8.sp, fontWeight = FontWeight(500))
        }
    }

}