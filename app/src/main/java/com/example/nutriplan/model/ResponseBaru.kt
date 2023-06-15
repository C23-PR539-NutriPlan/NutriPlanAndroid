package com.example.nutriplan.model

import com.google.gson.annotations.SerializedName

data class ResponseBaru(

	@field:SerializedName("listStory")
	val listStory: ListStory,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class ListStoryItem(

	@field:SerializedName("image")
	val image: Any,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("calories")
	val calories: Any
)

data class ListStory(
    @field:SerializedName("user_calories")
    val userCallories: Any,

	@field:SerializedName("user_recommendation")
	val userRecommendation: List<ListStoryItem>,

	@field:SerializedName("user_allergies")
	val userAllergies: List<String>,

	@field:SerializedName("user_preferences")
	val userPreferences: List<String>
)
