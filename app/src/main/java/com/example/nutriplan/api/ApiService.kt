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


    @GET("user/{id}")
    suspend fun getProfile(
        @Header("Authorization") token :String,
        @Path("id") id : String
    ):ProfileResponse
}