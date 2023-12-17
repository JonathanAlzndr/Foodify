package com.jonathan.mybook.data.retrofit

import com.jonathan.mybook.data.model.ResponseMeal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php?f=")
    fun searchMeals(@Query("f") firstLetter: String): Call<ResponseMeal>
}