package com.example.psychologistsapp.models

import java.io.Serializable

data class Location(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
): Serializable