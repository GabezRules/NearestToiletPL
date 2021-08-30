package com.gabez.locationiq_api.api.location_iq

import com.gabez.locationiq_api.ApiKeys
import com.gabez.locationiq_api.Env
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LocationIQReverseGeocodingInterface {

    @Headers("Content-Type: application/json", "Accept: text/html, application/json")
    @GET("/v1/reverse.php?format=json&key="+ ApiKeys.locationIQ_APIkey)
    suspend fun getCurrentCountry(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Response<String>

    companion object {
        fun create(): LocationIQReverseGeocodingInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(Env.reverseGeocodingURL)
                .build()
            return retrofit.create(LocationIQReverseGeocodingInterface::class.java)
        }
    }
}