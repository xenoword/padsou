package com.example.padsou

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.padsou.models.User
import com.example.padsou.navigation.NavGraph
import com.example.padsou.ui.components.Input
import com.example.padsou.ui.pages.Onboarding.Onboarding
import com.example.padsou.ui.components.NavigateButton
import com.example.padsou.ui.components.Title
import com.example.padsou.ui.pages.Auth.Login
import com.example.padsou.ui.theme.*
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PadsouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightGray
                ) {
                    NavGraph()
                }
            }
        }
    }
}