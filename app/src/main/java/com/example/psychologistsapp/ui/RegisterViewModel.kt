package com.example.psychologistsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB

class RegisterViewModel: ViewModel() {
    private val _errorMessages: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val errorMessages: LiveData<String> get() = _errorMessages
    private val _goToLoginScreen: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val goToLoginScreen: LiveData<Boolean> get() = _goToLoginScreen
    fun registerUser(name: String, lastName: String, email: String, password: String, phoneNumber: String) {
        if(name.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty()) {
            var user = User(name, lastName, email, password, phoneNumber)
            if (UsersDB.users.any { it.email == email && it.password == password}) {
                _errorMessages.value = "El email ya está registrado"
                return
            }else{
                UsersDB.users.add(user)
                _errorMessages.value = "Usuario correctamente registrado"
                _goToLoginScreen.value = true
            }
        } else {
            _errorMessages.value = "Rellene todos los campos"
        }
    }
}