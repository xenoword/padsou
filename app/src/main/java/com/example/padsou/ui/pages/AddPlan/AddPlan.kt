package com.example.padsou.ui.pages.AddPlan

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import android.graphics.Paint.Align
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.models.Plan
import com.example.padsou.ui.components.*
import com.example.padsou.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.accompanist.pager.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalPagerApi::class, ExperimentalSnapperApi::class)
@Composable
fun AddPlan(navController: NavHostController) {
    // PLAN DATA
    var currentPlan = Plan()
    // IMAGE SELECTION STUFF ---------------
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    // ------------------------------

    var titleValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    var linkValue by remember { mutableStateOf("") }
    var pagerState = rememberPagerState(0)

    val scope = MainScope()

    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize(),
        color = MediumBlue
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Divider(thickness = 40.dp, color = Color.Transparent)
            Column(
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Title("AJOUTER", Color.White)
                Divider(thickness = 0.dp, color = Color.Transparent)
                Text(
                    "Un bon plan pour en faire\n" +
                            "profiter les autres",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Divider(thickness = 40.dp, color = Color.Transparent)
            Surface(
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = LightGray
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp, vertical = 15.dp)
                ) {

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
                                        .padding(bottom = 15.dp)
                        )

                        HorizontalPager(
                            count = 2,
                            state = pagerState,
                            //userScrollEnabled = false, TODO
                        ) { page: Int ->
                            when (page) {
                                // PAGE 1 ---------------------------------
                                0 -> {
                                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                                        Title(text = "TITRE", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(
                                            name = titleValue, onValueChange = { titleValue = it },
                                            placeholder = "Abonnement 1 an Basic-Fit"
                                        )

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(
                                            text = "DESCRIPTION",
                                            color = Color.Black,
                                            size = 18.sp
                                        )
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(name = descriptionValue, onValueChange = { descriptionValue = it },
                                                placeholder = "Ne soit pas trop bavard, on s’en fout, va à l’essentiel",
                                                lineCount = 5)

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(text = "LIEN", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(
                                            name = linkValue, onValueChange = { linkValue = it },
                                            placeholder = "www.lienverstonbonplan.com"
                                        )

                                        Spacer(modifier = Modifier.weight(1f))
                                        Button(colors = ButtonDefaults.buttonColors(backgroundColor = MediumBlue),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 30.dp, bottom = 10.dp)
                                                .height(50.dp),
                                            shape = RoundedCornerShape(20F),
                                            onClick = {
                                                scope.launch {
                                                    currentPlan = Plan(
                                                        title = titleValue,
                                                        description = descriptionValue,
                                                        link = linkValue
                                                    )
                                                    pagerState.scrollToPage(1)
                                                }
                                            },
                                            //Actif seulement quand les 3 champs sont renseignés
                                            // TODO RETIRER LE PREMIER '!' EN VF
                                            enabled = (!titleValue.isNullOrBlank() &&
                                                    !descriptionValue.isNullOrBlank() &&
                                                    !linkValue.isNullOrBlank())
                                        ) {
                                            Text(
                                                "SUIVANT",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp,
                                                fontStyle = FontStyle.Normal,
                                                fontFamily = Typography.h1.fontFamily,
                                                lineHeight = 21.sp,
                                                textAlign = TextAlign.Center,
                                                color = Color.White
                                            )
                                        }

                                        Divider(thickness = 60.dp, color = Color.Transparent)
                                    }
                                }

                                // PAGE 2 ---------------------------------
                                1 -> {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(
                                            text = "PHOTO DU BON PLAN",
                                            color = Color.Black,
                                            size = 18.sp
                                        )

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        IconButton(
                                            onClick = {
                                                launcher.launch("image/*")
                                            },
                                            modifier = Modifier
                                                .size(200.dp)
                                                .background(
                                                    MediumBlue,
                                                    RoundedCornerShape(10.dp)
                                                )
                                        ) {
                                            imageUri?.let {
                                                if (Build.VERSION.SDK_INT < 28) {
                                                    bitmap.value = MediaStore.Images
                                                        .Media.getBitmap(context.contentResolver, it)

                                                } else {
                                                    val source = ImageDecoder
                                                        .createSource(context.contentResolver, it)
                                                    bitmap.value = ImageDecoder.decodeBitmap(source)
                                                }
                                            }

                                            bitmap.value?.let { btm ->
                                                Image(
                                                    bitmap = btm.asImageBitmap(),
                                                    contentDescription = null,
                                                    modifier = Modifier.fillMaxSize()
                                                )
                                            }

                                            if (bitmap.value == null) {
                                                Icon(
                                                    Icons.Rounded.Add, null,
                                                    Modifier.fillMaxSize(0.4f),
                                                    tint = LightGray
                                                )
                                            }
                                        }

                                        //Divider(thickness = 100.dp, color = Color.Transparent)
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = 100.dp, bottom = 60.dp),
                                            verticalAlignment = Alignment.Bottom
                                        ) {
                                            Button(colors = ButtonDefaults.buttonColors(backgroundColor = MediumBlue),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 0.dp, bottom = 10.dp)
                                                    .height(50.dp),
                                                shape = RoundedCornerShape(20F),
                                                onClick = {
                                                    scope.launch {
                                                        // TODO
                                                        uploadPlan(currentPlan, bitmap.value!!)
                                                        navController.navigate("Home")
                                                    }
                                                },
                                                //Actif seulement quand la photo est ajoutée
                                                enabled = (bitmap.value != null)
                                            )
                                            {
                                                Text(
                                                    "AJOUTER CE BON PLAN",
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 16.sp,
                                                    fontStyle = FontStyle.Normal,
                                                    fontFamily = Typography.h1.fontFamily,
                                                    lineHeight = 21.sp,
                                                    textAlign = TextAlign.Center,
                                                    color = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    NavBar(navController = navController)
                }
            }
        }
    }
}

private fun uploadPlan(plan: Plan, imgBitmap: Bitmap) {
    // Create a storage reference from our app
    val storageRef = Firebase.storage.reference

    // Create a reference to "image.jpg"
    val randName = (0..999999999).random().toString()
    val imgRef = storageRef.child("$randName.jpg")

    val imgImagesRef = storageRef.child("images/$randName.jpg")

    val baos = ByteArrayOutputStream()
    imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    var uploadTask = imgRef.putBytes(data)

    val urlTask = uploadTask.addOnFailureListener { ex ->
        //TODO HANDLE FAILURES
        throw ex
    }.addOnSuccessListener { task ->
        task.storage.downloadUrl.addOnSuccessListener {
            val downloadUri = it.toString()
            plan.image = downloadUri
            plan.nbTest = 0
            plan.note = 0
            plan.subTitle = ""
            insertPlanInDatabase(plan)
        }.addOnFailureListener{ ex ->
            //TODO HANDLE FAILURES
            throw ex
        }
    }
}

private fun insertPlanInDatabase(plan: Plan) {
    val databaseRef = Firebase.firestore

    databaseRef.collection("plans").document()
        .set(plan.toFirebaseHashMap())
        .addOnSuccessListener { Log.d(TAG, "Plan créé avec succès!")}
        .addOnFailureListener { e -> Log.w(TAG, "Erreur lors de la création du plan !", e) }
}