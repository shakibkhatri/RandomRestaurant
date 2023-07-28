package com.example.randomrestaurant.API

import com.example.randomrestaurant.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val base_url = "https://random-data-api.com/api/"

interface ApiInterface {

    @GET("restaurant/random_restaurant")
     fun getRestaurant(@Query("name")name: String): Call<ApiResponse>
}

object RetrofitObject {

    val apiInterface:ApiInterface
    init {
        val getInstance:Retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                apiInterface = getInstance.create(ApiInterface::class.java)

    }


}