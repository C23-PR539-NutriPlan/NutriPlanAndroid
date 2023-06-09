package com.example.nutriplan.feature.signup

import androidx.lifecycle.ViewModel
import com.example.nutriplan.repository.Repository

class SignUpViewModel(private val repository: Repository) : ViewModel() {
    fun saveUserRegister(name: String, email: String, password: String) = repository.saveUserRegister(name, email, password)

}