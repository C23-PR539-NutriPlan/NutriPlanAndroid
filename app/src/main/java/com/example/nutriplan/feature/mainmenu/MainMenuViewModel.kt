package com.example.nutriplan.feature.mainmenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nutriplan.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainMenuViewModel(private val repository: Repository): ViewModel() {

    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)



}