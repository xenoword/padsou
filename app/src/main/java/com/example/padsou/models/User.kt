package com.example.padsou.models

data class User(
    val id: Int,
    val pseudo: String,
    val profilePicture: Int,
    val mail: String,
    val password: String
)