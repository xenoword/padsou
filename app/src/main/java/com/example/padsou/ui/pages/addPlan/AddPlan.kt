package com.example.padsou.ui.pages.addPlan

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.padsou.models.Plan
import com.example.padsou.ui.components.*
import com.example.padsou.ui.theme.*
import com.google.accompanist.pager.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalPagerApi::class)
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

    var titleValue by remember { mutableStateOf("") }
    var descriptionValue by remember { mutableStateOf("") }
    var linkValue by remember { mutableStateOf("") }
    val pagerState = rememberPagerState(0)

    val scope = MainScope()

    Surface(
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
                            userScrollEnabled = false,
                        ) { page: Int ->
                            when (page) {
                                // PAGE 1 ---------------------------------
                                0 -> {
                                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                                        Title(text = "TITRE", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(name = titleValue, onValueChange = { titleValue = it },
                                                placeholder = "Abonnement 1 an Basic-Fit")

                                        Divider(thickness = 20.dp, color = Color.Transparent)
                                        Title(text = "DESCRIPTION", color = Color.Black, size = 18.sp)
                                        Divider(thickness = 5.dp, color = Color.Transparent)
                                        Input(name = descriptionValue, onValueChange = { descriptionValue = it },
                                                placeholder = "Ne soit pas trop bavard, on s???en fout, va ?? l???essentiel",
                                                lineCount = 5)

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
                                                        currentPlan = Plan(
                                                            title = titleValue,
                                                            description = descriptionValue,
                                                            link = linkValue
                                                        )
                                                        pagerState.scrollToPage(1)
                                                    }
                                                },
                                            //Actif seulement quand les 3 champs sont renseign??s
                                            enabled = (titleValue.isNotBlank() &&
                                                    descriptionValue.isNotBlank() &&
                                                    linkValue.isNotBlank())
                                        ) {
                                            Text("SUIVANT", fontWeight = FontWeight.Bold, fontSize = 16.sp, fontStyle = FontStyle.Normal,
                                                fontFamily = Typography.h1.fontFamily, lineHeight = 21.sp, textAlign = TextAlign.Center, color = Color.White)
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
                                                onClick = { launcher.launch("image/*") },
                                                modifier = Modifier
                                                        .size(200.dp)
                                                        .background(MediumBlue, RoundedCornerShape(10.dp))) {
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
                                        Row(modifier = Modifier
                                                .fillMaxSize()
                                                .padding(bottom = 70.dp),
                                                verticalAlignment = Alignment.Bottom) {
                                            Button(colors = ButtonDefaults.buttonColors(backgroundColor = MediumBlue),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 0.dp, bottom = 10.dp)
                                                    .height(50.dp),
                                                shape = RoundedCornerShape(20F),
                                                onClick = {
                                                    scope.launch {
                                                        // TODO
                                                        uploadPlan(currentPlan, bitmap.value!!, context)
                                                        navController.navigate("Home")
                                                    }
                                                },
                                                //Actif seulement quand la photo est ajout??e
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
                Row(modifier = Modifier
                        .fillMaxSize(),
                        verticalAlignment = Alignment.Bottom) {
                    NavBar(navController = navController)
                }
            }
        }
    }
}

private fun uploadPlan(plan: Plan, imgBitmap: Bitmap, context: Context) {
    // Create a storage reference from our app
    val storageRef = Firebase.storage.reference

    // Create a reference to "image.jpg"
    val randName = (0..999999999).random().toString()
    val imgRef = storageRef.child("$randName.jpg")

    val baos = ByteArrayOutputStream()
    imgBitmap.compress(Bitmap.CompressFormat.JPEG, 25, baos)
    val data = baos.toByteArray()

    val uploadTask = imgRef.putBytes(data)

    uploadTask.addOnFailureListener { ex ->
        //TODO HANDLE FAILURES
        throw ex
    }.addOnSuccessListener { task ->
        task.storage.downloadUrl.addOnSuccessListener {
            val downloadUri = it.toString()
            plan.image = downloadUri
            plan.nbTest = 0
            plan.note = (0..500).random() / 100.0
            plan.subTitle = ""
            plan.authorId = Firebase.auth.currentUser!!.uid
            insertPlanInDatabase(plan)

            Toast.makeText(context, "Le plan ?? ??t?? ajout??",
                Toast.LENGTH_LONG).show()
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
        .addOnSuccessListener { Log.d(TAG, "Plan cr???? avec succ??s!")}
        .addOnFailureListener { e -> Log.w(TAG, "Erreur lors de la cr??ation du plan !", e) }
}