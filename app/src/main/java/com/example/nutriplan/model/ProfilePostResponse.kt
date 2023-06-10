package com.example.nutriplan.model

import com.google.gson.annotations.SerializedName

data class ProfilePostResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
