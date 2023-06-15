package com.example.nutriplan.feature.dietplan.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers

class DetailPlanViewModel(private val repository: Repository):ViewModel() {

    fun getID() = repository.getID().asLiveData(Dispatchers.IO)

    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)
    fun getSpesificFood(foodID:Int,userID:String,token :String) = repository.getSpesificFood(foodID,userID,token)

    fun postLike(foodID:Int,userID:String,token: String) = repository.postLike(foodID,userID,token)
}
