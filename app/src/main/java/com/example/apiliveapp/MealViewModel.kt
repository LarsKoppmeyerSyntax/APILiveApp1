package com.example.apiliveapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiliveapp.data.MealsApi
import com.example.apiliveapp.data.model.Meal
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealViewModel(application: Application) : AndroidViewModel(application) {

    val apiService = MealsApi.retrofitService

    private val _randomMeal = MutableStateFlow<Meal?>(null)
    val randomMeal = _randomMeal.asStateFlow()

    private val _searchedMeals = MutableStateFlow<List<Meal>>(emptyList())
    val searchedMeals = _searchedMeals.asStateFlow()

    init {

        loadRandomMeal()

        searchMeals("Beef")

    }

    fun searchMeals(searchTearm: String) {
        viewModelScope.launch {
            try {
                val result = apiService.searchMeals(searchTearm)
                Log.d("MealResponse", "$result")

                _searchedMeals.value = result.meals



            } catch (ex: JsonDataException) {
                Log.i("MealApiError", "$ex")

                //TODO: Toast senden oder Info an User weiterleiten dass nichts gefunden wurde
                _searchedMeals.value = emptyList()

            } catch (ex : Exception) {
                Log.e("MealApiError", "$ex")

                _searchedMeals.value = emptyList()
            }

        }
    }

    fun loadRandomMeal() {
        viewModelScope.launch {
            try {
                val response = apiService.getRandomMeal()
                val newMeal = response.meals.first()
                _randomMeal.value = newMeal

            } catch (ex: JsonDataException) {

                //TODO: Leeres Suchergebnis verarbeiten
                //z.B. Toast senden
                Log.i("MealApiError", "$ex")


            } catch (ex : Exception) {
                Log.e("MealApiError", "$ex")
            }

        }
    }

}