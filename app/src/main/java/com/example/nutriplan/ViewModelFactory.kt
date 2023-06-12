package com.example.nutriplan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nutriplan.di.Injection
import com.example.nutriplan.feature.dietplan.DietPlanViewModel
import com.example.nutriplan.feature.form.FormViewModel
import com.example.nutriplan.feature.login.LoginViewModel
import com.example.nutriplan.feature.mainmenu.MainMenuViewModel
import com.example.nutriplan.feature.profile.ProfileViewModel
import com.example.nutriplan.feature.signup.SignUpViewModel
import com.example.nutriplan.repository.Repository

class ViewModelFactory private constructor(private val  repository:Repository):
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(MainMenuViewModel::class.java)){
            return MainMenuViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(FormViewModel::class.java)){
            return FormViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DietPlanViewModel::class.java)){
            return DietPlanViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }


    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }
}