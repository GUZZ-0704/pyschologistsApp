package com.example.psychologistsapp.models

import java.io.Serializable

class Appointment(
    var date: String = "",
    var psychologist: User = User(),
    var patient: User = User(),
    var location: Location = Location(),
    var note: Note = Note(),
    var review: Review = Review(),
    var isDone: Boolean = false,
    var type: String = "",
    var price: Double = 0.0,
    var time: String = "",
    var isPaid: Boolean = false,
    var isAtHome: Boolean = false
) : Serializable {

}
