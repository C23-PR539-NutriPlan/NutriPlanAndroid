package com.example.nutriplan.model

import com.google.gson.annotations.SerializedName

data class PostPlanResponses(
	@field:SerializedName("PostPlanResponses")
	val postPlanResponses: List<PostPlanResponsesItem>
)

data class PostPlanResponsesItem(

	@field:SerializedName("RecipeCategory")
	val recipeCategory: String,

	@field:SerializedName("RecipeIngredientParts")
	val recipeIngredientParts: String,

	@field:SerializedName("SaturatedFatContent")
	val saturatedFatContent: Any,

	@field:SerializedName("CarbohydrateContent")
	val carbohydrateContent: Any,

	@field:SerializedName("FatContent")
	val fatContent: Any,

	@field:SerializedName("ProteinContent")
	val proteinContent: Any,

	@field:SerializedName("SodiumContent")
	val sodiumContent: Any,

	@field:SerializedName("SugarContent")
	val sugarContent: Any,

	@field:SerializedName("Calories")
	val calories: Any,

	@field:SerializedName("CholesterolContent")
	val cholesterolContent: Any,

	@field:SerializedName("FiberContent")
	val fiberContent: Any,

	@field:SerializedName("Name")
	val name: String
)
