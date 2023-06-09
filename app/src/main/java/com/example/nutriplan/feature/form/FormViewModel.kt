package com.example.nutriplan.feature.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers


class FormViewModel(private val repository: Repository): ViewModel() {

    fun getID() = repository.getID().asLiveData(Dispatchers.IO)
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun postProfile(id:String,height : Int?,weight : Int?, weightGoal : Int, gender:String,age : Int?,allergies : List<String>,preferences:List<String>,token:String) = repository.postProfile(id,height, weight, weightGoal, gender, age, allergies, preferences,token)

}
