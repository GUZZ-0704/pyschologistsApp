package com.example.psychologistsapp.models

class WelcomeMessage(
    var id: Int,
    var messageImage: Int
) {
    override fun toString(): String {
        return "WelcomeMessage(id=$id, messageImage='$messageImage')"
    }


}