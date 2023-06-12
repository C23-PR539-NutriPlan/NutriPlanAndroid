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
        @Path("id") id : String
    ):GetAllFoodResponse

    @GET("food/{foodID}/{userID}")
    suspend fun getSpesificFood(
        @Path("foodID") foodID : Int,
        @Path("userID") ID : String
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
    ): ProfilePostResponse

    @GET("user/{id}")
    suspend fun getProfile(
        @Path("id") id : String
    ):ProfileResponse

    @FormUrlEncoded
    @POST("like")
    suspend fun postLike(
        @Field("foodID") foodID: Int,
        @Field("userID") password: String
    ): PostLikeResponse
}