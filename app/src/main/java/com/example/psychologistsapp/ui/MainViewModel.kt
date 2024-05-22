package com.example.psychologistsapp.ui

import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.R
import com.example.psychologistsapp.models.WelcomeMessage
import com.example.psychologistsapp.models.WelcomeMessagesDB

class MainViewModel: ViewModel() {
    fun setupData(){
        WelcomeMessagesDB.welcomeMessages.add(WelcomeMessage(1, R.drawable.prueba1))
        WelcomeMessagesDB.welcomeMessages.add(WelcomeMessage(2, R.drawable.prueba2))
    }
}