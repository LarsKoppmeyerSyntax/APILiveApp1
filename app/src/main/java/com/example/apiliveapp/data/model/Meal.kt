package com.example.apiliveapp.data.model

import com.squareup.moshi.Json

data class Meal(

    @Json(name = "strMeal")
    val name : String,

)