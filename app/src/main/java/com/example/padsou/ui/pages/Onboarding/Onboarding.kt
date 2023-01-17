package com.example.padsou.ui.pages.Onboarding

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.ui.components.NavigateButton
import com.example.padsou.ui.components.Title
import com.example.padsou.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(navController: NavHostController)
{
    val pagerState = rememberPagerState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Purple)
            .padding(top = 80.dp)
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
            Modifier.height(380.dp).padding(top = 80.dp)
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
                        .fillMaxHeight(0.05F),
                )
                HorizontalPager(
                    count = 3,
                    state = pagerState
                )
                {
                    Row(
                        Modifier
                            .fillMaxHeight(0.95F)
                            .fillMaxWidth(),
                        Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            Modifier
                                .fillMaxHeight(0.8F)
                                .width(230.dp)
                                .clip(shape = RoundedCornerShape(30.dp))
                                .background(White)
                                .padding(10.dp)
                        ) {
                            Row(
                                Modifier
                                    .fillMaxHeight(0.5F)
                                    .fillMaxWidth()) {
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(0.5F)
                                        .background(color = Teal200)
                                ) {

                                }
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .background(color = Purple200)
                                ) {

                                }
                            }
                            Row(
                                Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()) {
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(0.5F)
                                        .background(color = Purple500)
                                ) {

                                }
                                Column(
                                    Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .background(color = Purple700)
                                ) {

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
            .align(Alignment.CenterHorizontally),
            text = "Accède aux 500 bons plans qu'on te propose chaque mois",
            fontSize = 16.sp,
            color = White,
            textAlign = TextAlign.Center,
            )
        //button
        Row(modifier = Modifier.fillMaxWidth(0.7F).fillMaxHeight().align(Alignment.CenterHorizontally).padding(bottom = 30.dp),Arrangement.Center, Alignment.Bottom) {
            NavigateButton(
                text = "C'EST PARTI !",
                backgroundcolor = Pink,
                navController = navController,
                classDestination = "Onboarding"
            )
        }
    }
}
