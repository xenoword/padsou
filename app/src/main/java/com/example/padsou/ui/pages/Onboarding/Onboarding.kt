package com.example.padsou.ui.pages.Onboarding

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.padsou.ui.theme.Purple200
import com.example.padsou.ui.theme.Teal200
import com.example.padsou.ui.theme.White

@Composable
fun Onboarding()
{
    Column() {
        Text(text = "Pas de sous?")
        Text(text = "Y'a padsou")
        Row(
            Modifier.height(400.dp)
        ) {
            Column() {
                Row(
                    Modifier
                        .background(Purple200)
                        .fillMaxHeight(0.25F)
                        .padding(horizontal = 100.dp, vertical = 20.dp)
                        .fillMaxWidth(),
                    Arrangement.Center,
                    Alignment.Bottom
                ) {
                    Box(
                        Modifier
                            .width(30.dp)
                            .height(5.dp)
                            .padding(horizontal = 2.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = White, shape = RoundedCornerShape(40.dp)))
                    Box(
                        Modifier
                            .width(30.dp)
                            .height(5.dp)
                            .padding(horizontal = 2.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = White, shape = RoundedCornerShape(40.dp)))
                    Box(
                        Modifier
                            .width(30.dp)
                            .height(5.dp)
                            .padding(horizontal = 2.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = White, shape = RoundedCornerShape(40.dp)))
                }
                Row(
                    Modifier
                        .background(Teal200)
                        .fillMaxHeight(0.75F)
                        .fillMaxWidth(),
                    Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier
                            .fillMaxHeight(0.8F)
                            .width(190.dp)
                            .clip(shape = RoundedCornerShape(30.dp))
                            .background(White)
                            .padding(all = 30.dp),
                    ) {
                        Row {
                            Column() {

                            }
                            Column() {

                            }
                        }
                        Row {
                            Column() {

                            }
                            Column() {

                            }   
                        }
                    }
                }
            }
        }
        Text(text = "Accède aux 500 bons plans qu'on te propose chaque mois")
        Button(onClick = { /*TODO*/ }) {
            
        }
    }
}
