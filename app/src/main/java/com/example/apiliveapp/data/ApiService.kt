package com.example.apiliveapp.data

import com.example.apiliveapp.data.model.MealsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val loggingInterceptor = HttpLoggingInterceptor().apply {
    // Logging Levels: BODY, BASIC, NONE, HEADERS
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface APIService {


    @GET("random.php")
    suspend fun getRandomMeal() : List<MealsResponse>



}

object MealsApi {
    val retrofitService: APIService by lazy { retrofit.create(APIService::class.java) }
}
