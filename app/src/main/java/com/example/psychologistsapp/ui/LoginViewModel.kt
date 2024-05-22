package com.example.psychologistsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB

class LoginViewModel: ViewModel() {
    private val _errorMessages: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val errorMessages: LiveData<String> get() = _errorMessages
    private val _goToHomeScreen: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val goToHomeScreen: LiveData<Boolean> get() = _goToHomeScreen
    private lateinit var user : User
    fun login(email: String, password: String) {
        val user = UsersDB.users.find { it.email == email && it.password == password }
        if(user != null) {
            this.user = user
            _goToHomeScreen.value = true
        } else {
            _errorMessages.value = "Email or password incorrect"
        }
    }

    fun getUser(): User {
        return user
    }
}