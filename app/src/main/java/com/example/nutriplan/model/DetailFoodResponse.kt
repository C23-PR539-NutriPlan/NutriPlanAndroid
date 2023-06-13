package com.example.nutriplan.model

import com.google.gson.annotations.SerializedName

data class DetailFoodResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("story")
	val story: Story
)

data class Story(

	@field:SerializedName("sugarContent")
	val sugarContent: Any,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("proteinContent")
	val proteinContent: Any,

	@field:SerializedName("fiberContent")
	val fiberContent: Any,

	@field:SerializedName("like")
	val like: Boolean,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("fatContent")
	val fatContent: Any,

	@field:SerializedName("cholesterolContent")
	val cholesterolContent: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("carbohydrateContent")
	val carbohydrateContent: Any,

    @field:SerializedName("calories")
    val calories: Any

)
