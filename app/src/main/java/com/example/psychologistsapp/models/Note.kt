package com.example.psychologistsapp.models

import java.io.Serializable

class Note (
    var date: String = "",
    var patient : User = User(),
    var description: String = ""
): Serializable{
    var id: Int = 0
}

