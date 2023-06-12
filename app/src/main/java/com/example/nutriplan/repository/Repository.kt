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
    suspend fun clearID(){
        authDataStore.clearId()
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

//    private fun generateBearerToken(token: String):String{
//        return if (token.contains("bearer",true)){
//            token
//        }else{
//            token
//        }
//    }

    private fun generateBearerID(id: String):String{
        return  if(id.contains("bearer",true)){
            id
        }else{
            id
        }
    }
    fun getProfile(id : String) : LiveData<com.example.nutriplan.repository.Result<ProfileResponse>> = liveData(Dispatchers.IO) {
        emit(com.example.nutriplan.repository.Result.Loading)
        try {
//            val id3 :String = getID()
            val response = apiService.getProfile(generateBearerID(id))
            emit(com.example.nutriplan.repository.Result.Success(response))
        }catch (e:Exception){
            emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
        }
    }

    fun getAllFood(id:String): LiveData<com.example.nutriplan.repository.Result<GetAllFoodResponse>> = liveData(Dispatchers.IO){
        emit(com.example.nutriplan.repository.Result.Loading)
        try {
            val response = apiService.getAllFood(generateBearerID(id))
            emit(com.example.nutriplan.repository.Result.Success(response))
        }catch (e:Exception){
            emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
        }
    }

    fun getSpesificFood(foodID:Int,userID:String): LiveData<com.example.nutriplan.repository.Result<DetailFoodResponse>> = liveData(Dispatchers.IO){
        emit(com.example.nutriplan.repository.Result.Loading)
        try {
            val response = apiService.getSpesificFood(foodID,generateBearerID(userID))
            emit(com.example.nutriplan.repository.Result.Success(response))
        }catch (e:Exception){
            emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
        }
    }

    fun postProfile(id: String,height : Int?,weight : Int?, weightGoal : Int, gender:String,age : Int?,allergies : List<String>,preferences:List<String>) : LiveData<com.example.nutriplan.repository.Result<ProfilePostResponse>> = liveData(Dispatchers.IO){
        emit(com.example.nutriplan.repository.Result.Loading)
        try {
            val response = apiService.postProfile(generateBearerID(id),height, weight, 0, gender, age,  allergies, preferences)
            emit(com.example.nutriplan.repository.Result.Success(response))
        }catch (e:Exception){
            emit(com.example.nutriplan.repository.Result.Error(e.message.toString()))
        }
    }

    fun postLike(foodID: Int,userID: String) : LiveData<com.example.nutriplan.repository.Result<PostLikeResponse>> = liveData(Dispatchers.IO){
        emit(com.example.nutriplan.repository.Result.Loading)

        try {
            val response = apiService.postLike(foodID,generateBearerID(userID))
            emit(com.example.nutriplan.repository.Result.Success(response))
        }catch (e: Exception){
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
