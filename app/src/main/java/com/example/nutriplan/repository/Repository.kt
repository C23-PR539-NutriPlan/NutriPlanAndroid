package com.example.nutriplan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nutriplan.api.ApiService
import com.example.nutriplan.data.AuthDataStore
import com.example.nutriplan.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class Repository (private val apiService: ApiService, private val authDataStore: AuthDataStore,){
    fun getToken(): Flow<String?> = authDataStore.getToken()

    fun getID() : Flow<String?> = authDataStore.getID()

    private suspend fun saveToken(token: String) {
        authDataStore.saveToken(token)
    }
    private suspend fun saveID(id: String){
        authDataStore.saveID(id)
    }

    suspend fun clearToken() {
        authDataStore.clearToken()
    }

    fun getUserLogin(email: String, password: String): LiveData<com.example.nutriplan.repository.Result<LoginResponse>> = liveData(Dispatchers.IO) {
            emit(com.example.nutriplan.repository.Result.Loading)
            try {
                val response = apiService.login(email, password)
                saveToken(response.data!!.token)
                saveID(response.data!!.user)
                emit(com.example.nutriplan.repository.Result.Success(response))
            } catch (e: Exception) {
                emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
            }
        }

    fun saveUserRegister(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData(Dispatchers.IO) {
        emit(com.example.nutriplan.repository.Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(com.example.nutriplan.repository.Result.Success(response))
        } catch (e: Exception) {
            emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
        }
    }

    private fun generateBearerToken(token: String):String{
        return if (token.contains("bearer",true)){
            token
        }else{
            token
        }
    }

    private fun generateBearerID(id: String):String{
        return  if(id.contains("bearer",true)){
            id
        }else{
            id
        }
    }
    fun getProfile(token: String,id : String) : LiveData<com.example.nutriplan.repository.Result<ProfileResponse>> = liveData(Dispatchers.IO) {
        emit(com.example.nutriplan.repository.Result.Loading)
        try {
//            val id3 :String = getID()
            val response = apiService.getProfile(generateBearerToken(token),generateBearerID(id))
            emit(com.example.nutriplan.repository.Result.Success(response))
        }catch (e:Exception){
            emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
        }
    }




    companion object{
        @Volatile
        private var instance : Repository? = null
        fun getInstance(apiService: ApiService,authDataStore: AuthDataStore):Repository = instance ?: synchronized(this){
            instance ?:Repository(apiService,authDataStore)
        }.also { instance = it }

    }
}
