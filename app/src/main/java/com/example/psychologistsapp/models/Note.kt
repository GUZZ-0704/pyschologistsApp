package com.example.psychologistsapp.models

import java.io.Serializable

class Note : Serializable{
    var id: Int = 0
    var date: String = ""
    var patient : User = User()
    var description: String = ""
}

