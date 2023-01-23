package com.example.padsou.models

data class Plan(
    val id: Int,
    val title: String,
    val subTitle: String,
    val image: Int,
    val author: User,
    val nbTest: Int,
    val note: Int,
    val description: String,
    val link: String
)