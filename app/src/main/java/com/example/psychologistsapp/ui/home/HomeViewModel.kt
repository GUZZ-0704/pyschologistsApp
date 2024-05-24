package com.example.psychologistsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.psychologistsapp.models.Category
import com.example.psychologistsapp.models.User
import com.example.psychologistsapp.models.UsersDB

class HomeViewModel: ViewModel(){
    private val _categorySelected: MutableLiveData<ArrayList<Category>> by lazy {
        MutableLiveData<ArrayList<Category>>(arrayListOf())
    }
    val categorySelected:LiveData<ArrayList<Category>> get() = _categorySelected

    fun addCategory(category: Category) {
        val currentCategories = _categorySelected.value ?: arrayListOf()
        currentCategories.add(category)
        _categorySelected.value = currentCategories
    }

    fun removeCategory(category: Category) {
        _categorySelected.value?.remove(category)
        _categorySelected.value = _categorySelected.value
    }


    fun clearFilters() {
        _categorySelected.value?.clear()
    }

    fun filterPsychologists(): List<User> {
        return UsersDB.getPyshologists().filter { it.pyschologistSpecialties.any { categorySelected.value?.contains(it) == true } }
    }

    fun isCategorySelected(category: Category): Boolean {
        return _categorySelected.value?.contains(category) == true
    }

}