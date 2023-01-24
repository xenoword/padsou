package com.example.padsou.models

data class User(
    var id: String = "",
    val pseudo: String = "",
    val profilePicture: String = "",
    val mail: String = "",
    val password: String = ""
)