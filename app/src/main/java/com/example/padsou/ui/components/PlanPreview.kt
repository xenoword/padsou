package com.example.padsou.ui.components

import androidx.compose.foundation.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.padsou.models.Plan

@Composable
fun PlanPreview(plan: Plan, navController: NavHostController, size: Dp){

    Column(
        Modifier
            .size(size)
            .clickable {
                navController.navigate("Detail/"+plan.id)
            }
    ) {
        Box(
            Modifier
                .fillMaxHeight(0.7F)
                .fillMaxWidth()){
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
                    model = plan.author.profilePicture,
                    contentDescription = "Author profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.3F * 0.7F)
                        .fillMaxHeight(0.3F)
                        .border(
                            BorderStroke(2.dp, Color.White),
                            CircleShape
                        )
                        .clip(CircleShape)
                )
            }
        }

        Row {
            Text(text = plan.title, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Row {
            Text(text = plan.subTitle, fontSize = 8.sp, fontWeight = FontWeight(500))
        }
    }

}