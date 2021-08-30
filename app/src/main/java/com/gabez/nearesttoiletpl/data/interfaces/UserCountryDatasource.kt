package com.gabez.nearesttoiletpl.data.interfaces

import com.gabez.nearesttoiletpl.location.SearchBoundaries
import retrofit2.Response

interface UserCountryDatasource {
    suspend fun getUserLocationCountry(lat: String, lon: String): Response<String>
    suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String>
}