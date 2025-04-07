package com.shankhanilsaha.makemymeal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.getValue
import kotlin.jvm.java

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ApiServices {
    @GET("categories.php")
    suspend fun getCategories() : CategoryResponse
}

object ApiSetup {
    val retrofitServices : ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }
}