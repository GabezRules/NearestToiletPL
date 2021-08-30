package com.gabez.locationiq_api.api.location_iq

import com.gabez.locationiq_api.ApiKeys
import com.gabez.locationiq_api.Env
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LocationIQAutocompleteInterface {
    @Headers("Content-Type: application/json", "Accept: text/html, application/json")
    @GET("/v1/autocomplete.php?&q=toaleta&countrycodes=pl&accept-language=pl&bounded=1&limit=5&format=json&key="+ ApiKeys.locationIQ_APIkey)
    suspend fun getToiletsNearby(
        @Query("viewbox") vb: String,
    ): Response<String>

    companion object {
        fun create(): LocationIQAutocompleteInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(Env.autocompleteURL)
                .build()
            return retrofit.create(LocationIQAutocompleteInterface::class.java)
        }
    }
}