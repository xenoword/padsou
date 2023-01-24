package com.example.padsou.ui.pages.Onboarding

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.R
import com.example.padsou.models.Plan
import com.example.padsou.models.User
import com.example.padsou.ui.components.NavigateButton
import com.example.padsou.ui.components.PlanPreview
import com.example.padsou.ui.components.Title
import com.example.padsou.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.math.roundToInt

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(navController: NavHostController)
{
    val pagerState = rememberPagerState()

    val plans = mutableStateListOf<Plan>()

    val db = Firebase.firestore

    //get all plans and their authors
    db.collection("plans")
        .limit(12)
        .get()
        .addOnSuccessListener { result ->
            plans.clear()
            for (document in result) {
                val plan: Plan = document.toObject()
                plan.id = document.id

                db.collection("users")
                    .document(plan.authorId)
                    .get()
                    .addOnSuccessListener { docUser ->
                        val user: User? = docUser.toObject()
                        if(user != null){
                            plan.author = user
                        }
                    }
                plans.add(plan)
            }
        }

    Column(
        Modifier
            .fillMaxSize()
            .background(Purple)
            .padding(top = 60.dp)
    ) {
        //title 1
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Title(text = "Pas de sous?", color = White)
        }
        //title 2
        Row(Modifier.align(Alignment.CenterHorizontally)) {
            Title(text = "Y'a padsou.", color = Pink)
        }
        //Caroussel
        Row(
            Modifier.padding(top = 60.dp)
        ) {
            Column() {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = White,
                    inactiveColor = WhiteTransparent,
                    indicatorWidth = 20.dp,
                    indicatorHeight = 5.dp,
                    spacing = 8.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(40.dp)
                        .padding(bottom = 30.dp),
                )
                HorizontalPager(
                    count = 3,
                    state = pagerState
                )
                {
                    if (plans.size != 0) {
                        Row(
                            Modifier,
                            Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                Modifier
                                    .clip(shape = RoundedCornerShape(30.dp))
                                    .background(White)
                                    .padding(10.dp)
                            ) {
                                Row {
                                    Column(
                                        Modifier
                                            .padding(10.dp, 10.dp, 5.dp, 5.dp)
                                    ) {
                                        PlanPreview(
                                            plan = plans[0],
                                            navController = navController,
                                            height = 120.dp,
                                            width = 120.dp
                                        )
                                    }
                                    Column(
                                        Modifier
                                            .padding(5.dp, 10.dp, 10.dp, 5.dp)
                                    ) {
                                        PlanPreview(
                                            plan = plans[0],
                                            navController = navController,
                                            height = 120.dp,
                                            width = 120.dp
                                        )
                                    }
                                }
                                Row {
                                    Column(
                                        Modifier
                                            .padding(10.dp, 10.dp, 5.dp, 5.dp)
                                    ) {
                                        PlanPreview(
                                            plan = plans[0],
                                            navController = navController,
                                            height = 120.dp,
                                            width = 120.dp
                                        )
                                    }
                                    Column(
                                        Modifier
                                            .padding(5.dp, 10.dp, 10.dp, 5.dp)
                                    ) {
                                        PlanPreview(
                                            plan = plans[0],
                                            navController = navController,
                                            height = 120.dp,
                                            width = 120.dp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //description
        Text(modifier = Modifier
            .fillMaxWidth(0.6F)
            .align(Alignment.CenterHorizontally)
            .padding(top=30.dp),
            text = "Accède aux 500 bons plans qu'on te propose chaque mois",
            fontSize = 16.sp,
            color = White,
            textAlign = TextAlign.Center,
            )
        //button
        Row(modifier = Modifier
            .fillMaxWidth(0.7F)
            .fillMaxHeight()
            .align(Alignment.CenterHorizontally)
            .padding(bottom = 30.dp),Arrangement.Center, Alignment.Bottom) {
            NavigateButton(
                text = "C'EST PARTI !",
                backgroundcolor = Pink,
                navController = navController,
                classDestination = "Login"
            )
        }
    }
}
