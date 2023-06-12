package com.example.nutriplan.feature.dietplan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers

class DietPlanViewModel(private val repository: Repository):ViewModel() {
    fun getID()  = repository.getID().asLiveData(Dispatchers.IO)

    fun getAllFood(id:String) = repository.getAllFood(id)
}