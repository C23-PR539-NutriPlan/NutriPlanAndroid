package com.example.nutriplan.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository): ViewModel() {

    fun clearToken(){
        viewModelScope.launch { repository.clearToken() }
    }
    fun getID() = repository.getID().asLiveData(Dispatchers.IO)
    fun getProfile(token : String,id : String) = repository.getProfile(token,id)
}