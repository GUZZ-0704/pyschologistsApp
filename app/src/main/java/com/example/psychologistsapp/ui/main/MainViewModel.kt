package com.example.psychologistsapp.ui

import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.R
import com.example.psychologistsapp.models.Appointment
import com.example.psychologistsapp.models.Category
import com.example.psychologistsapp.models.Location
import com.example.psychologistsapp.models.Note
import com.example.psychologistsapp.models.Review
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB
import com.example.psychologistsapp.models.WelcomeMessage
import com.example.psychologistsapp.models.WelcomeMessagesDB

class MainViewModel: ViewModel() {
    fun setupData(){
        setupWelcomeMessage()
        setupUsers()
        setupAppointments()
    }


    private fun setupWelcomeMessage(){
        WelcomeMessagesDB.welcomeMessages.add(WelcomeMessage(1, R.drawable.prueba4))
        WelcomeMessagesDB.welcomeMessages.add(WelcomeMessage(2, R.drawable.prueba3))
    }

    private fun setupUsers(){
        UsersDB.users.add(User("Juan", "Perez", "jperez@test.com", "1234", "1234567890"))
        UsersDB.users.add(User("Franco", "Sebastian", "fsebastian@gmail.com", "fse", "1224567890", true,"Licenciado en Psicología","https://contents.bebee.com/users/id/9mDWG6512f7f43943f/_avatar-DPZCd-400.png",
            arrayListOf(Category.PSYCHOLOGY, Category.PSYCHIATRY)))
        UsersDB.users.add(User("Diego", "Santos","dsantos@test.com", "dsan", "1234467890", true,"Licenciado en Psiquiatría","https://photos.psychologytoday.com/3732cb4e-c67b-48a5-bc56-5775656c2466/2/320x400.jpeg"
            , arrayListOf(Category.PSYCHIATRY)))
        UsersDB.users.add(User("Martha", "Malferte","mmalferte@test.com", "smal", "345678", true,"Licenciado en Psicología","https://photos.psychologytoday.com/ea9fd385-6456-486c-9d11-a00018db3513/5/320x400.jpeg"
            , arrayListOf(Category.PSYCHOLOGY, Category.PSYCHOTHERAPY)))
        UsersDB.users.add(User("Lucia", "Gomez","lgomez@test.com", "lgom", "1234567890", true,"Licenciado en Psicología","https://s3-eu-west-1.amazonaws.com/doctoralia.es/doctor/05ec1a/05ec1a1d0d226f83dfc0cc3ef455d54a_large.jpg"
            , arrayListOf(Category.PSYCHOLOGY, Category.PSYCHOTHERAPY, Category.PSYCHIATRIC_NURSING)))
        UsersDB.users.add(User("Carlos", "Perez","cperez@test.com", "1234", "1234567890"))
    }

    private fun setupAppointments(){
        UsersDB.users[5].appointments.add(Appointment(
            "20-10-2021",
            UsersDB.users[1],
            UsersDB.users[5],
            Location(longitude = 0.0, latitude = 0.0),
            Note(date = "20-10-2021", description = "Paciente con problemas de ansiedad", patient = UsersDB.users[5]),
            Review( 5.0, "Excelente atención", UsersDB.users[5]),
            false,
            "Presencial",
            1000.0,
            "10:00",
            false,
            false
        ))
    }

}