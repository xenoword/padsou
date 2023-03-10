package com.example.padsou.ui.pages.onboarding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(navController: NavHostController)
{
    val pagerState = rememberPagerState()

    val plans by remember { mutableStateOf(mutableListOf<Plan>()) }

    auth = Firebase.auth

    val currentUser = auth.currentUser
    val redirectionPage = if(currentUser != null) "Home" else "Login"


    val db = Firebase.firestore

    var pagerCount by remember { mutableStateOf(1) }

    //get all plans and their authors
    LaunchedEffect(Unit) {
        db.collection("plans")
            .limit(12)
            .get()
            .addOnSuccessListener { result ->
                plans.clear()
                for (document in result) {
                    val plan: Plan = document.toObject()
                    plan.id = document.id

                    Log.d("UwU", plan.authorId)
                    db.collection("users")
                        .document(plan.authorId)
                        .get()
                        .addOnSuccessListener { docUser ->
                            val user: User? = docUser.toObject()
                            if(user != null){
                                plan.author = user
                            }
                            plans.add(plan)
                            pagerCount = if (plans.size != 0) kotlin.math.ceil(plans.size / 4.0).toInt() else 1
                        }
                }
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
            Column {
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
                    count = pagerCount,
                    state = pagerState
                )
                {
                        page: Int ->
                    Row(
                        Modifier,
                        Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .size(290.dp)
                                .clip(shape = RoundedCornerShape(30.dp))
                                .background(White)
                                .padding(10.dp)
                        ) {
                            if (plans.size != 0) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    userScrollEnabled = false,
                                ) {
                                    items(4) { i ->
                                        if(plans.size > i+page*4) {
                                            Column(
                                                Modifier
                                                    .padding(10.dp, 10.dp, 5.dp, 5.dp)
                                            ) {
                                                PlanPreview(
                                                    plan = plans[i + page*4],
                                                    navController = navController,
                                                    size = 120.dp,
                                                )
                                            }
                                        }
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
            .padding(top = 30.dp),
            text = "Acc??de aux 500 bons plans qu'on te propose chaque mois",
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
                classDestination = redirectionPage
            )
        }
    }
}
