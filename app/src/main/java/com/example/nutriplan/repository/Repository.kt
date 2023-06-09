package com.example.nutriplan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nutriplan.api.ApiService
import com.example.nutriplan.data.AuthDataStore
import com.example.nutriplan.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow


class Repository (private val apiService: ApiService,private val apiService2: ApiService, private val authDataStore: AuthDataStore,){
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

    fun getUserLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.login(email, password)
                saveToken(response.data.token)
                saveID(response.data.user)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun saveUserRegister(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    private fun generateBearerToken(token: String):String{
        return if (token.contains("bearer",true)){
            token
        }else{
            "Bearer $token"
        }
    }

    private fun generateBearerID(id: String):String{
        return  if(id.contains("bearer",true)){
            id
        }else{
            id
        }
    }
    fun getProfile(id : String,token: String) : LiveData<Result<ProfileResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
//            val id3 :String = getID()
            val response = apiService.getProfile(generateBearerID(id),generateBearerToken(token))
            emit(Result.Success(response))
        }catch (e:Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAllFood(id:String,token: String): LiveData<Result<ResponseBaru>> = liveData(Dispatchers.IO){
        emit(Result.Loading)
        try {
            val response = apiService.getAllFood(generateBearerID(id),generateBearerToken(token))
            emit(Result.Success(response))
        }catch (e:Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSpesificFood(foodID:Int,userID:String,token: String): LiveData<Result<DetailFoodResponse>> = liveData(Dispatchers.IO){
        emit(Result.Loading)
        try {
            val response = apiService.getSpesificFood(foodID,generateBearerID(userID),generateBearerToken(token))
            emit(Result.Success(response))
        }catch (e:Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postProfile(id: String,height : Int?,weight : Int?, weightGoal : Int, gender:String,age : Int?,allergies : List<String>,preferences:List<String>,token: String) : LiveData<Result<ProfilePostResponse>> = liveData(Dispatchers.IO){
        emit(Result.Loading)
        try {
            val response = apiService.postProfile(generateBearerID(id),height, weight, 0, gender, age,  allergies, preferences,generateBearerToken(token))
            emit(Result.Success(response))
        }catch (e:Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postLike(foodID: Int,userID: String,token: String) : LiveData<Result<PostLikeResponse>> = liveData(Dispatchers.IO){
        emit(Result.Loading)

        try {
            val response = apiService.postLike(foodID,generateBearerID(userID),generateBearerToken(token))
            emit(Result.Success(response))
        }catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postForRecomm(user_id : String, user_calories : Any,user_allergies: List<String>?,user_favorites: List<String>?): LiveData<Result<PostPlanResponses>> = liveData(Dispatchers.IO){
        emit(Result.Loading)
        try {
            val request = ApiService.PostPlanRequest(user_id, user_calories, user_allergies, user_favorites)
            val response = apiService2.postPlan(request)
            emit(Result.Success(response))
        }catch (e:Exception){
            emit(Result.Error(e.message.toString()))
        }

    }

    companion object{
        @Volatile
        private var instance : Repository? = null
        fun getInstance(apiService: ApiService,apiService2:ApiService,authDataStore: AuthDataStore):Repository = instance ?: synchronized(this){
            instance ?:Repository(apiService,apiService2,authDataStore)
        }.also { instance = it }

    }
}
