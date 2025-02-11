package com.example.apiliveapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiliveapp.data.MealsApi
import com.example.apiliveapp.data.model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealViewModel(application: Application) : AndroidViewModel(application) {

    val apiService = MealsApi.retrofitService

    private val _randomMeal = MutableStateFlow<Meal?>(null)
    val randomMeal = _randomMeal.asStateFlow()

    init {

        loadRandomMeal()

    }

    fun loadRandomMeal() {
        viewModelScope.launch {
            try {
                val response = apiService.getRandomMeal()
                val newMeal = response.first().meals.first()
                _randomMeal.value = newMeal

            } catch (ex : Exception) {
                Log.e("ApiFehler", ex.toString())
            }

        }
    }

}