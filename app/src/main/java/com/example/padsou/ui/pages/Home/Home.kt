package com.example.padsou.ui.pages.Home

import android.util.Log
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.padsou.models.Plan
import com.example.padsou.ui.components.PlanPreview
import com.google.android.gms.common.util.IOUtils
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


@Composable
fun Home(navController: NavHostController) {

    Divider(thickness =  40.dp, color = Color.Transparent)
    Text(text = "SWEET HOME ALABAMA")
}