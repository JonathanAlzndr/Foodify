package com.jonathan.mybook.views.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.mybook.data.model.MealsItem
import com.jonathan.mybook.data.model.ResponseMeal
import com.jonathan.mybook.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {


    // MutableLiveData to hold the list of meals
    private val _mealsLiveData = MutableLiveData<List<MealsItem?>?>()
    val mealsLiveData: LiveData<List<MealsItem?>?> = _mealsLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        searchMealsByFirstLetter(SEARCH)
    }

    fun searchMealsByFirstLetter(firstLetter: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchMeals(firstLetter)
        client.enqueue(object : Callback<ResponseMeal> {
            override fun onResponse(
                call: Call<ResponseMeal>,
                response: Response<ResponseMeal>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val mealResponse = response.body()
                    _mealsLiveData.value = mealResponse?.meals
                }
            }

            override fun onFailure(call: Call<ResponseMeal>, t: Throwable) {
                _isLoading.value = false
                Log.e("com.jonathan.mybook.views.home.MainViewModel", "onFailure: ${t.message.toString()}")
            }

        })

    }

    companion object {
        private const val SEARCH = "c"
    }
}