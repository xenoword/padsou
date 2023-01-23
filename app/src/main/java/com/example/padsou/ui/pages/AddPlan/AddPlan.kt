package com.example.padsou.ui.pages.AddPlan

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.ui.components.*
import com.example.padsou.ui.theme.LightGray
import com.example.padsou.ui.theme.MediumBlue
import com.example.padsou.ui.theme.White
import com.example.padsou.ui.theme.WhiteTransparent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AddPlan(navController: NavHostController) {
    var titleValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    var linkValue by remember { mutableStateOf("") }
    val pagerState = rememberPagerState(0)
    val scope = MainScope()

    androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize(),
            color = MediumBlue) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Divider(thickness = 40.dp, color = Color.Transparent)
            Column(modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Title("AJOUTER", Color.White)
                Divider(thickness = 0.dp, color = Color.Transparent)
                Text("Un bon plan pour en faire\n" +
                        "profiter les autres",
                        color = Color.White,
                        fontSize = 20.sp)
            }

            Divider(thickness = 40.dp, color = Color.Transparent)
            Surface(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                    color = LightGray) {
                Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp, vertical = 15.dp)
                        .verticalScroll(rememberScrollState())) {

                    Column(modifier = Modifier.fillMaxWidth()) {
                        HorizontalPagerIndicator(
                                pagerState = pagerState,
                                activeColor = MediumBlue,
                                inactiveColor = Color.LightGray,
                                indicatorWidth = 50.dp,
                                indicatorHeight = 5.dp,
                                spacing = 10.dp,
                                modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxHeight(0.05F)
                                        .padding(bottom = 30.dp)
                        )
                        HorizontalPager(
                                count = 2,
                                state = pagerState
                        ) { page: Int ->
                            when (page) {
                                // PAGE 1 ---------------------------------
                                0 -> {
                                    Column(modifier = Modifier.fillMaxSize()) {
                                        Title(text = "TITRE", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(name = titleValue, onValueChange = { titleValue = it },
                                                placeholder = "Abonnement 1 an Basic-Fit")

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(text = "DESCRIPTION", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(name = descriptionValue, onValueChange = { descriptionValue = it },
                                                placeholder = "Ne soit pas trop bavard, on s’en fout, va à l’essentiel",
                                                lineCount = 6)

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(text = "LIEN", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(name = linkValue, onValueChange = { linkValue = it },
                                                placeholder = "www.lienverstonbonplan.com")

                                        Spacer(modifier = Modifier.weight(1f))
                                        Button(colors = ButtonDefaults.buttonColors(backgroundColor = MediumBlue),
                                                modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(top = 30.dp, bottom = 10.dp)
                                                        .height(50.dp),
                                                shape = RoundedCornerShape(20F),
                                                onClick = {
                                                    scope.launch {
                                                        pagerState.scrollToPage(1)
                                                    }
                                                }
                                        ) {
                                            Text("SUIVANT", fontWeight = FontWeight.Bold, fontSize = 16.sp, fontStyle = FontStyle.Normal,
                                                    lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
                                        }

                                        Divider(thickness = 60.dp, color = Color.Transparent)
                                    }
                                }

                                // PAGE 2 ---------------------------------
                                1 -> {
                                    Column(modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally) {

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(text = "PHOTO DU BON PLAN", color = Color.Black, size = 18.sp)

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        IconButton(
                                                onClick = { /* TODO */ },
                                                modifier = Modifier
                                                        .size(200.dp)
                                                        .background(MediumBlue, RoundedCornerShape(10.dp))) {
                                            Icon(Icons.Rounded.Add, null,
                                                    Modifier.fillMaxSize(0.4f),
                                                    tint = LightGray)
                                        }

                                        //Divider(thickness = 100.dp, color = Color.Transparent)
                                        Row(modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top= 100.dp, bottom = 60.dp),
                                                verticalAlignment = Alignment.Bottom) {
                                            NavigateButton(text = "AJOUTER CE BON PLAN",
                                                    backgroundcolor = MediumBlue,
                                                    navController = navController,
                                                    classDestination = "Home")
                                        }

                                    }
                                }
                            }
                        }
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