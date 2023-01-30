package com.example.padsou.models

data class User(
    var id: String = "",
    val pseudo: String = "",
    val profilePicture: String = "",
    val mail: String = ""
)
{

    public fun toFirebaseHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "mail" to mail,
            "pseudo" to pseudo,
            "profilePicture" to profilePicture,
        )
    }
}
