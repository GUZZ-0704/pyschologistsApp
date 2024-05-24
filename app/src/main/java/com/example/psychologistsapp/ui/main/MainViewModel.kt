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
        UsersDB.users.add(User("Franco", "Sebastian", "fsebastian@gmail.com", "fse", "1224567890", true,"Licenciado en Psicología","https://contents.bebee.com/users/id/9mDWG6512f7f43943f/_avatar-DPZCd-400.png"))
        UsersDB.users.add(User("Diego", "Santos","dsantos@test.com", "dsan", "1234467890", true,"Licenciado en Psiquiatría","5 áños de experiencia, trabajo en 5 instituciones","https://photos.psychologytoday.com/3732cb4e-c67b-48a5-bc56-5775656c2466/2/320x400.jpeg"))
        UsersDB.users.add(User("Martha", "Malferte","mmalferte@test.com", "smal", "345678", true,"Licenciado en Psicología","https://photos.psychologytoday.com/ea9fd385-6456-486c-9d11-a00018db3513/5/320x400.jpeg"))
        UsersDB.users.add(User("Lucia", "Gomez","lgomez@test.com", "lgom", "1234567890", true,"Licenciado en Psicología","https://s3-eu-west-1.amazonaws.com/doctoralia.es/doctor/05ec1a/05ec1a1d0d226f83dfc0cc3ef455d54a_large.jpg"))

    }


}