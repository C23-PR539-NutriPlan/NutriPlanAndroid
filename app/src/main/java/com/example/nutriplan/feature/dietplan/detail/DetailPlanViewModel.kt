package com.example.nutriplan.feature.dietplan.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers

class DetailPlanViewModel(private val repository: Repository):ViewModel() {

    fun getID() = repository.getID().asLiveData(Dispatchers.IO)

    fun getSpesificFood(foodID:Int,userID:String) = repository.getSpesificFood(foodID,userID)

    fun postLike(foodID:Int,userID:String) = repository.postLike(foodID,userID)
}