package com.example.psychologistsapp.models

import java.io.Serializable

class Review : Serializable{
    var id: Int = 0
    var rating: Int = 0
    var comment: String = ""
    var user: User = User()
}