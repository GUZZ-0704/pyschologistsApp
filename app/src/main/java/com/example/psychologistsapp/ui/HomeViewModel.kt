package com.example.psychologistsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB

class HomeViewModel: ViewModel(){
    private val _psychologistList: MutableLiveData<ArrayList<User>> by lazy {
        MutableLiveData<ArrayList<User>>(null)
    }
    val psychologistList:LiveData<ArrayList<User>> get() = _psychologistList
    fun loadPsychologists() {
        _psychologistList.value = ArrayList(UsersDB.users.filter { it.isPsychologist })
    }


}