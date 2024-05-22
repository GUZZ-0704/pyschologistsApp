package com.example.psychologistsapp.models

class Review {
    var id: Int = 0
    var rating: Int = 0
    var comment: String = ""
    var user: User = User()
}