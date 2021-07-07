package com.gabez.nearesttoiletpl.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("v1/reverse.php")
    fun getCurrentCountry(
        @Query("key") API_KEY: String,
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("format") format: String
    ): Call<JSONObject>

    companion object {
        fun create(): RetrofitInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Env.apiUrl)
                .build()
            return retrofit.create(RetrofitInterface::class.java)
        }
    }
}