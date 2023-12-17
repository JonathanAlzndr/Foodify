package com.jonathan.mybook.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseMeal(
	val meals: List<MealsItem?>? = null
)

@Parcelize
data class MealsItem(
	val strImageSource: String? = null,
	val strCategory: String? = null,
	val strIngredient13: String? = null,
	val strIngredient15: String? = null,
	val idMeal: String? = null,
	val strInstructions: String? = null,
	val strMealThumb: String? = null,
	val strYoutube: String? = null,
	val strMeal: String? = null,
	val strSource: String? = null
) : Parcelable

