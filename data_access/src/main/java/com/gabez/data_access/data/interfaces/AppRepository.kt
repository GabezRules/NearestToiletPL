package com.gabez.data_access.data.interfaces

import com.gabez.data_access.SearchBoundaries
import retrofit2.Response

interface AppRepository {
    suspend fun getUserLocationCountry(lat: String, lon: String): Response<String>
    suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String>
}