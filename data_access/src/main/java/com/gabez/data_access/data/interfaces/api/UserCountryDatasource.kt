package com.gabez.data_access.data.interfaces.api

import com.gabez.data_access.Place
import com.gabez.data_access.SearchBoundaries
import kotlinx.coroutines.flow.Flow

interface UserCountryDatasource {
    suspend fun getUserLocationCountry(lat: String, lon: String): Flow<String>
    suspend fun getNearbyToilets(bounds: SearchBoundaries): Flow<List<Place>>
}