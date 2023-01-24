package com.example.padsou.ui.pages.PlanDetail

import android.graphics.drawable.Icon
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.padsou.R
import com.example.padsou.models.Plan
import com.example.padsou.models.User
import com.example.padsou.ui.components.ExternalLinkButton
import com.example.padsou.ui.components.Title
import com.example.padsou.ui.theme.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

@Composable
fun PlanDetail(planId: String) {
    val db = Firebase.firestore
    var plan: Plan by remember { mutableStateOf(Plan()) }

    db.collection("plans")
        .document(planId)
        .get()
        .addOnSuccessListener { document ->
            plan = document.toObject()!!
            plan!!.id = planId
        }

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundLightGray)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
        )
        {
            AsyncImage(
                model = plan.image,
                contentDescription = "Plan image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Black,
                            Color(0xD4)
                        )
                    )
                )
            )
            Column(
                Modifier
                    .fillMaxWidth(0.8F)
                    .align(Alignment.Center)) {
                Title(text = plan.title, color = White)
                Text(text = plan.subTitle, color = White, fontSize = 12.sp, fontWeight = FontWeight(700))
            }
        }
        Row(
            Modifier
                .fillMaxWidth(0.85F)
                .fillMaxHeight()
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Column {
                Column(
                    Modifier
                        .requiredHeightIn(100.dp, 300.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(30.dp))
                        .background(White)
                        .padding(20.dp)
                ) {
                    Row(Modifier.padding(bottom = 20.dp)) {
                        Box(Modifier.fillMaxWidth(0.15F))
                        {
                            AsyncImage(
                                model = plan.author.profilePicture,
                                contentDescription = "Author profile picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp)
                                    .border(
                                        BorderStroke(2.dp, Color.White),
                                        CircleShape
                                    )
                                    .clip(CircleShape)
                            )
                        }
                        Column(
                            Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth(0.5F)) {
                            Text(text = "Proposé par", fontWeight = FontWeight(500), color = LightGrayText)
                            Text(text = plan.author.pseudo, fontWeight = FontWeight(700))
                        }
                        //stars
                        Row(Modifier.fillMaxWidth() ) {
                            for (i in 1..5)
                            {
                                androidx.compose.material.Icon(
                                    painter = painterResource(id = R.drawable.vector),
                                    contentDescription = null,
                                    tint = if (plan.note >= i) YellowStar else GrayStar
                                )
                            }
                        }
                    }
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        Text(text = plan.description)
                    }
                }

                Text(
                    text = "TESTÉE PAR " + plan.nbTest + " GALÉRIEN" + if(plan.nbTest > 1) "S" else "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Row(modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 30.dp),Arrangement.Center, Alignment.Bottom) {
                    ExternalLinkButton(
                        text = "PROFITER DE L'OFFRE",
                        backgroundcolor = MediumBlue,
                        link = plan.link
                    )
                }
            }
        }
    }
}