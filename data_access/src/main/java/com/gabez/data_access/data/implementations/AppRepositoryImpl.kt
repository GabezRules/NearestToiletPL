package com.gabez.data_access.data.implementations

import com.gabez.data_access.Place
import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.data_access.SearchBoundaries
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val userCountryDatasource: UserCountryDatasource) : AppRepository {

    override suspend fun getUserLocationCountry(lat: String, lon: String): Flow<String> =
        userCountryDatasource.getUserLocationCountry(
            lat, lon
        )

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Flow<List<Place>> =
        userCountryDatasource.getNearbyToilets(bounds)
}