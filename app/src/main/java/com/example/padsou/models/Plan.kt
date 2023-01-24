package com.example.padsou.models

data class Plan(
    var id: String = "",
    val title: String = "",
    val subTitle: String = "",
    val image: String ="",
    val authorId: String = "",
    val nbTest: Int = 0,
    val note: Int = 0,
    val description: String = "",
    val link: String = "",
    var author: User = User()
)