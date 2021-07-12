package com.gabez.nearesttoiletpl.api.location_iq

import com.gabez.nearesttoiletpl.api.ApiKeys
import com.gabez.nearesttoiletpl.api.Env
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LocationIqInterface {

    @Headers("Content-Type: application/json", "Accept: text/html, application/json")
    @GET("v1/reverse.php?format=json&key="+ApiKeys.locationIQ_APIkey)
    fun getCurrentCountry(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Call<String>

    companion object {
        fun create(): LocationIqInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(Env.apiUrl)
                .build()
            return retrofit.create(LocationIqInterface::class.java)
        }
    }
}