package com.gabez.data_access.data.implementations

import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.data_access.data.interfaces.api.UserCountryDatasource
import com.gabez.data_access.SearchBoundaries
import retrofit2.Response
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(val userCountryDatasource: UserCountryDatasource) : AppRepository {

    override suspend fun getUserLocationCountry(lat: String, lon: String): Response<String> =
        userCountryDatasource.getUserLocationCountry(
            lat, lon
        )

    override suspend fun getNearbyToilets(bounds: SearchBoundaries): Response<String> =
        userCountryDatasource.getNearbyToilets(bounds)
}