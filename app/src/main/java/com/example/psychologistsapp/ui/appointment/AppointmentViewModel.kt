package com.example.psychologistsapp.ui.appointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.models.Appointment
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB

class AppointmentViewModel: ViewModel(){
    private val _appointmentList = MutableLiveData<ArrayList<Appointment>>(arrayListOf())
    val appointmentList: LiveData<ArrayList<Appointment>> = _appointmentList

    private val _user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
    val user: LiveData<User> get() = _user

    fun getAppointments() {
        val userAppointments = _user.value?.appointments
        val pendingAppointments = userAppointments?.filter { !it.isDone }
        _appointmentList.value = ArrayList(pendingAppointments)
    }
    fun getUser(user: User) {
        _user.value = user
    }
}