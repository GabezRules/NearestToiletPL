package com.gabez.data_access.data.interfaces.api

import com.gabez.data_access.SearchBoundaries
import retrofit2.Response

interface UserCountryDatasource {
    suspend fun getUserLocationCountry(lat: String, lon: String): Response<String>
    suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String>
}