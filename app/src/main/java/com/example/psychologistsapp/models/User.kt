package com.example.psychologistsapp.models

import java.io.Serializable


class User: Serializable {
    var id: Int = 0
    var name: String = ""
    var lastName : String = ""
    var userName: String = ""
    var email: String = ""
    var password: String = ""
    var phoneNumber: String = ""
    var location: Location = Location()
    var isPsychologist: Boolean = false
    var age: Int = 0
    var schedule: String = ""
    var description: String = ""
    var reviews: MutableList<Review> = mutableListOf()
    var rating: Double = 0.0
    var profilePicture: Int = 0
    var fee : Double = 0.0
    var qualifitcation : String = "" //Titulo
    var mastersDegree : String = "" //Maestria
    var criminalRecord: String = ""
    var curriculum: String = ""
    var idPhoto: String = ""
    var profileImage: String = ""
    var patientNotes: ArrayList<Note> = arrayListOf()
    var appointmentsAsPsychologist: ArrayList<Appointment> = arrayListOf()
    var appointmentsAsPatient: ArrayList<Appointment> = arrayListOf()

    constructor()
    constructor(name: String, lastName: String, email: String, password: String, phoneNumber: String){
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
        this.phoneNumber = phoneNumber
    }

    fun addReview(review: Review){
        reviews.add(review)
        updateRating()
    }

    fun deleteReview(review: Review){
        reviews.remove(review)
        updateRating()
    }

    fun updateRating(){
        var sum = 0
        for (review in reviews){
            sum += review.rating
        }
        rating = sum.toDouble() / reviews.size
    }

    fun addPatientNotes(note: Note){
        patientNotes.add(note)
    }

    fun deletePatientNotes(note: Note){
        patientNotes.remove(note)
    }

    fun updatePatientNotes(note: Note){
        for (i in patientNotes.indices){
            if (patientNotes[i].id == note.id){
                patientNotes[i] = note
                break
            }
        }
    }


}


