package com.example.nutriplan.feature.dietplan.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers

class PostDietPlanViewModel(private val repository: Repository): ViewModel() {
    fun getID()  = repository.getID().asLiveData(Dispatchers.IO)
    fun getToken()  = repository.getToken().asLiveData(Dispatchers.IO)

    fun postForRecomm(user_id : String, user_calories : Any,user_allergies: List<String>?,user_favorites: List<String>?) = repository.postForRecomm(user_id, user_calories, user_allergies, user_favorites)

    fun getAllFood(id:String,token : String) = repository.getAllFood(id,token)
}
