package com.example.padsou.models

data class Plan(
    var id: String = "",
    var title: String = "",
    var subTitle: String = "",
    var image: String ="",
    var authorId: String = "",
    var nbTest: Int = 0,
    var note: Double = 0.0,
    var description: String = "",
    var link: String = "",
    var author: User = User()
){
    fun toFirebaseHashMap(): HashMap<String, Any> {
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