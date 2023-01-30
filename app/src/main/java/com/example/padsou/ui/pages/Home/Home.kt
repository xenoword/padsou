package com.example.padsou.ui.pages.Home

import android.util.Log
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.models.Plan
import com.example.padsou.ui.components.PlanPreview
import com.example.padsou.ui.components.*
import com.example.padsou.ui.theme.LightGray
import com.example.padsou.ui.theme.MediumBlue
import com.example.padsou.R
import com.example.padsou.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(navController: NavHostController) {

    var plans by remember { mutableStateOf(mutableListOf<Plan>()) }
    val db = Firebase.firestore

    //get all plans and their authors
    db.collection("plans")
        .get()
        .addOnSuccessListener { result ->
            plans.clear()
            for (document in result) {
                var plan: Plan? = document.toObject()
                if (plan != null) {
                    plan.id = document.id

                    db.collection("users")
                        .document(plan.authorId)
                        .get()
                        .addOnSuccessListener { docUser ->
                            var user: User? = docUser.toObject()
                            if(user != null){
                                plan.author = user
                            }
                        }
                    plans.add(plan)
                }
            }
        }

    var searchValue by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(),
            color = MediumBlue) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Divider(thickness = 40.dp, color = Color.Transparent)
            Column(modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Title("COUCOU TOI,", Color.White)
                Divider(thickness = 0.dp, color = Color.Transparent)
                Text("T'es en manque de thunes ?",
                        color = Color.White,
                        fontSize = 20.sp)

                Divider(thickness = 40.dp, color = Color.Transparent)
                SearchBar(name = searchValue, onValueChange = { searchValue = it },
                        placeholder = "Cherche un bon plan")
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

                    Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 5.dp, vertical = 15.dp)
                    ) {
                        IconNavButtonWithText(navController = navController,
                                routeName = "Search/groceries",
                                painterResource(R.drawable.shopping_bag_48px),
                                MediumBlue, Color.White, "COURSES")
                        IconNavButtonWithText(navController = navController,
                                routeName = "Search/sport",
                                painterResource(R.drawable.directions_run_48px),
                                Color(0xFFF4696A), Color.White, "SPORT")
                        IconNavButtonWithText(navController = navController,
                                routeName = "Search/train",
                                painterResource(R.drawable.train_48px),
                                Color(0xFF579BFE), Color.White, "TRAINS")
                        IconNavButtonWithText(navController = navController,
                                routeName = "Search/party",
                                painterResource(R.drawable.celebration_48px),
                                Color(0xFF7C8CF9), Color.White, "SOIRÉES")
                    }

                    Column(modifier = Modifier.fillMaxWidth(0.85f).padding(bottom = 70.dp)) {
                        Divider(thickness = 15.dp, color = Color.Transparent)
                        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom) {

                            Title(text = "LES PLANS DU MOMENT", color = Color.Black, 14.sp)

                            ClickableText(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    text = buildAnnotatedString {
                                        withStyle(
                                                style = SpanStyle(
                                                        color = Color(0xFFF44336),
                                                        fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold),
                                        ) {
                                            append("Voir tout")
                                        }
                                    }, onClick = { navController.navigate("Search/") }
                            )
                        }

                        val cellSize = 160.dp
                        LazyVerticalGrid(
                                columns = GridCells.Fixed(2)
                        ) {
                            items(plans.size) { index ->
                                Card(shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                                .padding(3.dp)
                                        .size(cellSize)
                                        .fillMaxWidth()) {
                                    Box(modifier = Modifier.padding(10.dp)) {
                                        PlanPreview(plans[index], navController, cellSize)
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

@Composable
fun CategoryButton(navController: NavHostController, searchTerm: String,
                   iconImgVector: ImageVector) {
    IconButton(
            onClick = { /* navController.navigate( ... ) TODO */ },
            modifier = Modifier
                    .size(50.dp)
                    .background(LightGray, RoundedCornerShape(5.dp))) {
        Icon(iconImgVector, null,
                Modifier.fillMaxSize(0.8f),
                tint = MediumBlue)
    }

}