package com.example.nutriplan.api

import com.example.nutriplan.model.LoginResponse
import com.example.nutriplan.model.RegisterResponse
import com.example.nutriplan.model.UserItem
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


    @GET("users/{id}")
    suspend fun getProfile(
        @Header("Authorization") token :String,
        @Path("id") id : String
    ):UserItem
}