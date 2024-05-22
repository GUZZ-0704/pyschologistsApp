package com.example.psychologistsapp.ui

import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.R
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB
import com.example.psychologistsapp.models.WelcomeMessage
import com.example.psychologistsapp.models.WelcomeMessagesDB

class MainViewModel: ViewModel() {
    fun setupData(){
        setupWelcomeMessage()
        setupUsers()
    }

    private fun setupWelcomeMessage(){
        WelcomeMessagesDB.welcomeMessages.add(WelcomeMessage(1, R.drawable.prueba1))
        WelcomeMessagesDB.welcomeMessages.add(WelcomeMessage(2, R.drawable.prueba2))
    }

    private fun setupUsers(){
        UsersDB.users.add(User("Juan", "Perez", "jperez@test.com", "1234", "1234567890"))
    }

}