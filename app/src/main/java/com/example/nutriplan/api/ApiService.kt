package com.example.nutriplan.api

import com.example.nutriplan.model.*
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @GET("food/all/{id}")
    suspend fun getAllFood(
        @Path("id") id : String,
        @Header("Authorization") token :String
    ):ResponseBaru

    @GET("food/{foodID}/{userID}")
    suspend fun getSpesificFood(
        @Path("foodID") foodID : Int,
        @Path("userID") ID : String,
        @Header("Authorization") token :String
    ):DetailFoodResponse

    @FormUrlEncoded
    @POST("user/form/{id}")
    suspend fun postProfile(
        @Path("id") id : String,
        @Field("height") height: Int?,
        @Field("weight") weight: Int?,
        @Field("weightGoal") weightGoal: Int,
        @Field("gender") gender: String,
        @Field("age") age: Int?,
        @Field("allergies") allergies: List<String>?,
        @Field("preferences") preferences: List<String>?,
        @Header("Authorization") token :String
    ): ProfilePostResponse

    @GET("user/{id}")
    suspend fun getProfile(
        @Path("id") id : String,
        @Header("Authorization") token :String
    ):ProfileResponse

    @FormUrlEncoded
    @POST("like")
    suspend fun postLike(
        @Field("foodID") foodID: Int,
        @Field("userID") userID: String,
        @Header("Authorization") token :String
    ): PostLikeResponse


    @Headers("Content-Type: application/json")
    @POST("predict")
    suspend fun postPlan(
        @Body request : PostPlanRequest
    ):PostPlanResponses

    data class PostPlanRequest(
        val user_id: String,
        val user_calories: Any,
        val user_allergies: List<String>?,
        val user_favorites: List<String>?
    )
}
