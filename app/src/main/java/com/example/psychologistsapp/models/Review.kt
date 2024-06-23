package com.example.psychologistsapp.models

import java.io.Serializable

class Review (
    var rating: Double = 0.0,
    var comment: String = "",
    var user: User = User()
): Serializable{
    var id: Int = 0

}