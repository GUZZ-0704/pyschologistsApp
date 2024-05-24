package com.example.psychologistsapp.models

object UsersDB {
    var users = mutableListOf<User>()

    fun getPyshologists(): List<User> {
        return users.filter { it.isPsychologist }
    }
}
