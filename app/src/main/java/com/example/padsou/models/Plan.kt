package com.example.padsou.models

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

data class Plan(
    var id: String = "",
    var title: String = "",
    var subTitle: String = "",
    var image: String ="",
    var authorId: String = "",
    var nbTest: Int = 0,
    var note: Int = 0,
    var description: String = "",
    var link: String = "",
    var author: User = User()
){
    public fun toFirebaseHashMap(): HashMap<String, Any> {

        return hashMapOf(
            "authorId" to authorId,
            "description" to description,
            "image" to image,
            "link" to link,
            "nbTest" to nbTest,
            "note" to note,
            "title" to title,
            "subTitle" to subTitle,
        )
    }
}